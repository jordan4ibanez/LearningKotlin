import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random

// This is a runBlocking scope.
// You could use this in Main to have full access to coroutines across the entirety of your program.
fun doSomeAsync() = runBlocking {

    // This becomes a lightweight non-blocking thread
    launch {
        delay(10000)
        println("This was executed first!")
    }
    println("This was executed second!")

    // So does this
    (0..1000).forEach { i ->
        launch {
            delay(Random.nextLong(10_000))
//            println("hi $i")
        }
    }

    println("This was executed last")

    println("BEGIN NEXT EXECUTION")

    launch { testIt() }


    // Let's see if we can create an access violation

    // ArrayList<String>() is direct version of mutableListOf<String>()
    val myCoolList = mutableListOf<String>()

    (0..1_000_000).forEach { _ ->

        // This was a good test
        launch {
            delay(Random.nextLong(2))
            StringBuilder().also { builder ->
                for (i in 0..20) {
                    builder.append(Random.nextInt(255).toChar())
                }
//                println("appending ${builder.toString()}")
                myCoolList.add(builder.toString())
            }
        }

        // But let's make it harder
        launch {
            delay(Random.nextLong(2))

            // The List might be empty, this is completely random
            if (myCoolList.isEmpty()) return@launch

            val gotten = myCoolList[Random.nextInt(myCoolList.size)]
//            println("retrieving $gotten")
        }
    }

    // So that still didn't cause it, let's do something extra dumb
    var accumulator = 0

    val complexJob = launch {
        (0..5_000_000).forEach {
            launch {
//                if (Random.nextBoolean()) accumulator += 1 else accumulator -= 1
                accumulator++
            }
        }
    }

    complexJob.join()
    println("the accumulator is $accumulator")

    // I honestly have no idea how this doesn't crash, some kind of miracle

    // But this is the safest option

    val mutex = Mutex()
    var newAccumulator = 0

    val synchronizedJob = launch {
        (0..2_000_000).forEach {
            launch {
                mutex.withLock {
                    newAccumulator++
                }
            }
        }
    }

    synchronizedJob.join()
    println("synchronized accumulator: $newAccumulator")



}

// Suspend functions are pretty neat, useful
suspend fun testIt() {
    delay(10000L)
    println("I am alive woo")
}