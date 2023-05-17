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

    val test = sum(yeah, yeah)

    println(test)

    doesNothing(test)

    val myCoolRectangle = Rectangle(3.0,3)

    // This is pretty sick
    val words = "${myCoolRectangle.width}, ${myCoolRectangle.height.toDouble()}, ${myCoolRectangle.area}"
    val someAddition = 55

    println("$words and my cool addition is $someAddition")

    // This is crazy
    class LocalClass(var good: Boolean)
    fun LocalClass.scream() = println("ahhh also ${this.good}")
    fun boof() = println("Is this good? ${LocalClass(true).good}")

    boof()
    LocalClass(false).scream()

    fun max(a: Int, b: Int) = if (a > b) a else b

    println("Max of 1 and 2 is ${max(1, 2)}")

    // Different ways to iterate
    val myCoolList = listOf("1", "shoes", "3")
    for (item in myCoolList) {
        println(item)
    }
    myCoolList.forEach {
        println(it)
    }
    for (it in myCoolList.iterator()) {
        println(it)
    }
    myCoolList.forEach {
        // Get the first letter
        println(it[0])
    }
    var i = 0
    while (i < myCoolList.size) {
        println("list index $i is ${myCoolList[i]}")
        i++
    }

    // function with localized variable function
    fun get(index: Int): String {
        fun match(): String {
            return when (index) {
                1 -> "That's a one"
                2 -> "That's definitely a two"
                3 -> "Hmm, a three"
                else -> "I've never seen this number :O"
            }
        }
        return "Your input was $index. ${match()}"
    }
    for (w in 1..4) {
        println(get(w))
    }

    // anonymous function factory version
    fun getFun(index: Int): () -> Unit {
        fun match(): String {
            return when (index) {
                1 -> "That's a one"
                2 -> "That's definitely a two"
                3 -> "Hmm, a three"
                else -> "I've never seen this number :O"
            }
        }
        return fun() {
            println("anonymous version: ${match()}")
        }
    }
    for (w in 1..4) {
        getFun(w)()
    }

    // Even counter
    fun countEven(input: Int) {
        assert(input >= 2)
        for (q in 2..input step 2) {
            println(q)
        }
    }
    countEven(128)

    // Messing with lists
    val evenCoolerList = listOf("ford", "chevy", "3", "55", countEven(6).toString(), "hello world")
    evenCoolerList
        .filter { it.startsWith("f") || it.startsWith("c") }
        .sortedBy { it }
        .map { it }
        .forEach { println(it) }

    // Nullable
    var nully: String? = null
    nully = "test"
    nully = null
    println(nully == null)


    // This one is just for fun
    fun parseVoidFunction(input: () -> Unit) {
        input()
    }
    // First class functions woooooo
    val firstClass: () -> Unit = {
        println("I am void")
    }
    parseVoidFunction(firstClass)

    // Now even more fun
    var counter = 0
    fun parseRecursiveFunction(input: () -> Unit) {
        counter++
        if (counter > 55) {
            return
        }
        input()
    }
    // Has to be defined like this, or it cannot pass itself in
    fun aRecursiveFunction() {
        println("I am also void")
        parseRecursiveFunction(::aRecursiveFunction)
    }

    aRecursiveFunction()

    // Inline classes
    val tested = Points(1)
    println(tested.test)

    val tested2 = Points(33)
    println(tested2)


    // Immutable list
    val myReadOnlyList = listOf(1,2,3,4,5)
    println("myReadOnlyList at index 0 is ${myReadOnlyList[0]}")

    // Immutable map
    val myCoolMap = mapOf("there" to 1, "here" to 0, "anywhereElse" to 2)
    println("myCoolMap is here: ${myCoolMap["here"]}")

    // Now we iterate myCoolMap three ways
    for ((key, value) in myCoolMap) {
        println("$key -> $value")
    }
    // I think this one looks the coolest :)
    myCoolMap.forEach { (key, value) ->
        println("$key -> $value")
    }
    for(dataObject in myCoolMap) {
        println("${dataObject.key} -> ${dataObject.value}")
    }

    // Now THIS IS SO SICK AHHH

    // Range can be an immutable value :D
    val anInclusiveRange = 0..10

    for (numba in anInclusiveRange) {
        println("$numba is here")
    }

    val anExclusiveRange = 0 until 10

    for (numba in anExclusiveRange) {
        println("$numba is here")
    }

    // Scopes are pretty sweet
    {
        val tempBoi = "noice"
        println(tempBoi)
        // Goodbye tempBoi :'(
    }

    {{{{{
        println("why would you do this though?")
    }}}}}

    // Filtering
    val fruits = listOf("apple", "oranges", "automobile", "banana")

    if ("automobile" in fruits) {
        println("automobile is not a fruit ahhhHHHH")
    }

    // Terminal input
    print("What is your name: ")
    val userName = readln()

    print("On a scale of 1-3 how are you feeling: ")
    // Inlined print switch with string interpolation :D
    println( "$userName, " +
        when (readln().toInt()) {
            0 -> "wat"
            1 -> "that's not good"
            2 -> "I suppose that's okay"
            3 -> "happy to hear it!!"
            else -> "not feeling it, Mr Krabs?"
    } )



}

open class Shape

class Rectangle(val width: Number, val height: Number): Shape() {
    val area = width.toDouble().times(height.toDouble())
}

@JvmInline
value class Points(val test: Int)

