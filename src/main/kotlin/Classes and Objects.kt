import kotlin.random.Random

// Begin the super function
fun part2() {

    val emptyBoi = Empty()

    // That's kinda neat
    println(message = emptyBoi.toString())

    val blah = InitOrderDemo("hi")

    // This is nuts
    "hi there".also(::println)

    // Another way to do that
    "hello there".also { println(it) }

    // This works
    (123).also(::println)

    // So does this!
    (123).also { println(it) }

    // Can be used for functions!
    fun returnString(): String {
        return "this is a test"
    }

    returnString().also { println(it) }

    returnString().also(::println)

    // Functional chaining :D
    var greaterThanTen: Boolean
    val myCoolNumber = Random.nextInt(20).also { it -> greaterThanTen = (it > 10).also{ println("Was it greater than 10? $it") } }
    println("greater than ten? $greaterThanTen and the number was $myCoolNumber")

    Customer("frank")

    // Initialized via primary and secondary
    TrailingTest("ye", "oh ye"/*, left to default*/).toString().also(::println)
    TrailingTest2("yurp", "derp"/*, left to default*/).toString().also(::println)

    Nothing()

    MrGreeter()
    MrGreeter("Good day")

    MultipleConstructors().doTheBlah()
    MultipleConstructors(2).doTheBlah()
    MultipleConstructors("flarp").doTheBlah()

    // Can't create this class
    // YouCantCreateMe() <- NOPE!
    // YouCantCreateMe.blah() <- NOPE!

    // Needed a companion class to be...java-y
    YouCantCreateMe.realBlah()


}

class YouCantCreateMe private constructor() {
    // Impossible function :D
    fun blah() {
        println("i'm pretty blah!")
    }

    companion object {
        fun realBlah() {
            println("blah blah blah")
        }
    }
}

class MultipleConstructors {
    // Many constructors that stream into one value
    private val blah: String
    constructor() {
        blah = "Nothing :D"
    }
    constructor(inputNumber: Int) {
        blah = inputNumber.toString()
    }

    constructor(inputString: String) {
        blah = inputString
    }

    fun doTheBlah() {
        println("blah is $blah")
    }
}


class MrGreeter(greeting: String = "hi there") {

    // As you can see here, the localized variable is now passed into the initializer
    init {
        println("$greeting, traveler")
    }
    // Uncomment this to see: When the initializer goes out of scope, greeting is gone!
    // fun yeah() = println("$greeting")
}

// Class which has no constructor yet can initialize with code running
class Nothing {

    // A blank constructor, literally does nothing
    constructor()
    // Init doesn't exist for this constructor

    // "greeting" is totally unused
    constructor(greeting: String = "hi there")
    init {
        // Uncomment this to see that the local "greeting" variable does not get passed into the init
        // println("I'm nothing woooo, your greeting is: $greeting")

        // Uncomment this for a nice stack overflow :D
        // Nothing()
    }

    // Uncomment this to see that you cannot have duplicate constructors
    // constructor(shmeating: String = "hi there" ) {
    //    println("$shmeating")
    // }
}

data class Blah(val bloop: Boolean)

class TrailingTest(
    // Auto initialized values
    // This is a primary constructor
    val potatoes:  String,
    val tomatoes: String,
    val rotten: Boolean = false
) { init {println("flarp")} }// is equivalent to:
class TrailingTest2 {
    val potatoes: String
    val tomatoes: String
    val rotten: Boolean

    // Secondary constructor
    constructor(potatoes: String, tomatoes: String, rotten: Boolean = false) {
        this.potatoes = potatoes
        this.tomatoes = tomatoes
        this.rotten = rotten
        println("flarp")
    }

    override fun toString(): String {
        return "TrailingTest2: potatoes: $potatoes, tomatoes: $tomatoes, rotten: $rotten"
    }
}

// This started going backwards from this point on, I don't feel like scrolling down anymore. So scroll upwards to see more progress

class Customer(name: String) {
    val customerName = name.uppercase()

    init {
        customerName.also(::println)
    }
}


// You can omit the constructor key word
class VerbosePerson constructor(firstName: String)

class Person(firstName: String)

class Empty

class InitOrderDemo(name: String) {
    // It appears this pipes in the value into println
    val firstProperty = "First property: $name".also(::println)
    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)
    init {
        println("Second initializer block that prints ${name.length}")
    }
}