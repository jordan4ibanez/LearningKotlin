import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

const val blah: String = "good day lad!"

fun main() = runBlocking {
    launch {
        delay(10000L)
        println("hi")
    }
    launch {
        delay(1000)
        print("I come second")
    }
    println(blah)
}
