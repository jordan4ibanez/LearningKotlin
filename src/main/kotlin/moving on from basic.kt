fun noMoreBasicPlz() {

    // A very small example of delegate functions
    val test = CoolerBeans {
        println("Your number is 5")
    }
    test.cool()


    val printy: NumberPrinter = {
        println("123")
    }

    printy()

    val anotherTest = CoolerBeans(printy)

    anotherTest.cool()

    // Another way to instantiate that syntax
    val beans = CoolerBeans({println(5)})

    // Example as if I were utilizing this to create custom mobs or something.
    // This feels kind of like Lua syntax
    val zombie = Mob (
        onStep = {self ->
            self.memory += 1
        },
        debug = {self ->
            println("I have ${self.memory}")
        }
    )

    (1..10).forEach{ _ -> zombie.runLogic()}

    // lambda expression syntax
    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y}

    sum(1, 2).also(::println)

    // lambda expression syntax without optional annotations
    val sum2 = { x: Int, y: Int -> x + y}

    sum(1, 2).also(::println)

    val blah = { i: String -> println(i)}


    // So let's say we only want a string that's a number

    // functional
    fun sanitizeNumeric(input: String): Boolean {
        input.forEach {
            if (!it.isDigit()) return false
        }
        return true
    }

    // OOP
    fun String.sanitizeNumeric(): Boolean {
        this.forEach {
            if (!it.isDigit()) return false
        }
        return true
    }

    val testStrings = arrayOf("test", "test2", "test123", "123", "flarp1", "123s")

    println("BEGIN FUNCTIONAL")
    // functional
    testStrings.forEachIndexed { testID, word ->
        sanitizeNumeric(word).also{println("test $testID is sanitized? $it")}
    }

    println("BEGIN OOP")
    // OOP
    for ((testID, word) in testStrings.withIndex()) {
        word.sanitizeNumeric().also{println("test $testID is sanitized? $it")}
    }

    println("BEGIN ONE LINER FUNCTIONAL")
    // or we could have just done this
    testStrings.forEachIndexed { testID, word ->
        (word.toIntOrNull() ?: false).then{ println("test $testID is sanitized? ${it is Int}") }
    }

    /*
    Since doubles and floats are a lot harder to parse,
    the built-in Float and Double methods are a lot easier to work with.
    Notice that the last value has no "f" but it is still approved.
    */
    val arrayOfFloatStrings = arrayOf("12.123f", "123.349f", "12983.0f", "nope", "stillnot0.123f", "123.91")

    arrayOfFloatStrings.forEachIndexed { testID, word ->
        (word.toFloatOrNull() ?: false).then{ println("float test $testID is sanitized? ${it is Float}")}
    }


}

// Example 1: 0 argument delegate function
typealias NumberPrinter = () -> Unit

class CoolerBeans(val cool: NumberPrinter)

// Example 2: 2 argument delegate function, can be used for mobs, entities, etc

typealias OnStep = (Mob) -> Unit
typealias Debug = (Mob) -> Unit
class Mob(val onStep: OnStep, val debug: Debug) {

    var memory = 0

    fun runLogic() {
        // delta blah blah
        onStep(this)
        debug(this)
    }
}

