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