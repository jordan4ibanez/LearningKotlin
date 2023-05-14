import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger

const val blah: String = "good day lad!"

fun sum(a: Int, b: Int) = a + b

fun main() = runBlocking {
    val blah: AtomicInteger = AtomicInteger(2)
    repeat(5_000_000) {
        launch {
            if ((0..10_000).random() > 5_000) {
                blah.incrementAndGet()
                println("up $blah")
            } else {
                blah.decrementAndGet()
                println("down $blah")
            }
        }
    }
    println(blah)
}
