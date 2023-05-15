import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger

const val blah: String = "good day lad!"

fun sum(a: Number, b: Number) = a.toDouble().plus(b.toDouble()) as Number

fun doesNothing(a: Any) = println(a)

fun main() = run {
    val atomicCounter = AtomicInteger(2)
    runBlocking {
        repeat(50_000) {
            launch {
                if ((0..10_000).random() > 5_000) {
                    atomicCounter.set(sum(atomicCounter.get(), 5).toInt())
                } else {
                    atomicCounter.decrementAndGet()
                }
            }
        }
    }
    println("final output: $atomicCounter")

    val yeah = 5.30f

    var test = sum(yeah, yeah)

    println(test)

    doesNothing(test)

    val myCoolRectangle = Rectangle(3.0,3)

    // This is pretty sick
    val words = "${myCoolRectangle.width}, ${myCoolRectangle.height.toDouble()}, ${myCoolRectangle.area}"
    val someAddition = 55

    println("$words and my cool addition is $someAddition")

    // This is crazy
    class InlinedClass(var good: Boolean)
    fun InlinedClass.scream() = println("ahhh also ${this.good}")
    fun boof() = println("Is this good? ${InlinedClass(true).good}")

    boof()
    InlinedClass(false).scream()

    fun max(a: Int, b: Int) = if (a > b) a else b

    print("Max of 1 and 2 is ${max(1, 2)}")

}

open class Shape

class Rectangle(val width: Number, val height: Number): Shape() {
    val area = width.toDouble().times(height.toDouble())
}



