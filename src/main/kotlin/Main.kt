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
    run {
        val tempBoi = "noice"
        println(tempBoi)
        // Goodbye tempBoi :'(
    }

    run {
        run {
            run {
                run {
                    run {
                        println("why would you do this though?")
                    }
                }
            }
        }
    }

    // Filtering
    val fruits = listOf("apple", "oranges", "automobile", "banana")

    if ("automobile" in fruits) {
        println("automobile is not a fruit ahhhHHHH")
    }

    // This is disabled so I can keep learning
    // Terminal input
//    print("What is your name: ")
//    val userName = readln()
//
//    print("On a scale of 1-3 how are you feeling: ")
//    // Inlined print switch with string interpolation :D
//    println( "$userName, " +
//        when (readln().toInt()) {
//            0 -> "wat"
//            1 -> "that's not good"
//            2 -> "I suppose that's okay"
//            3 -> "happy to hear it!!"
//            else -> "not feeling it, Mr Krabs?"
//    } )

    // Generics
    fun classScanner(input: Any) {
        println(when (input) {
            is Int -> "Integer"
            is String -> "String"
            is Char -> "Char"
            is Float -> "Float"
            is Double -> "Double"
            else -> "WARNING! Unexpected type! Got: ${input.javaClass}"
        })
    }
    classScanner(1)
    classScanner(2.0)
    classScanner(2.3f)
    classScanner(1L)
    classScanner(Rectangle(1,2))

    // You can just do this apparently
    (1 until 10).forEach {numba ->
        println("$numba is pretty cool")
    }

    // Utilizing singleton
    println("Is single boi single? ${SingleBoi.single}")

    // Utilizing class that implements an abstract class
    jordan4ibanez().doArt()

    // Nullable testing
    class Blah {
        fun test(): String {
            return "success!"
        }
    }
    val nullBoi: Blah? = null

    println(nullBoi?.test())

    val isNull = nullBoi == null

    val testing = if (isNull) 1 else 0
    println("test class is null? $testing $isNull")


    // Single expression fun
    fun gimmePi() = 3.14
    fun gimmeLessPrecisePi() = 3.14f

    // Calling multiple methods within the scope of an object
    class Walker {
        var posX = 0
        var posY = 0
        fun stepLeft() = posX--
        fun stepRight() = posX++
        fun stepForward() = posY++
        fun stepBack() = posY--
        fun printPos() = println("I am at: $posX, $posY")

        var happiness = 0
    }
    val walkMan = Walker()
    // Here begins the scope
    with (walkMan) {
        (0 until 10).forEach { _ -> // <-This part is just to shut up warning
            stepForward()
        }
        (0 until 5).forEach { _ -> stepLeft() }
        printPos()
    }

    // Add MORE things to a class constructor :O
    // Alternatively, a completely BETTER way to do constructors!
    val moreWalkMan = Walker().apply {
        happiness = 10
    }
    println("moreWalkMan is feeling ${moreWalkMan.happiness} happiness")

    // Swapping two variables??
    var a = 1
    var b = 2
    // wat, how is this even possible
    a = b.also { b = a}

    // Basic number types
    var genericNumber: Number = 4

    var byte: Byte = 1
    var uByte: UByte = 2u

    var short: Short = 22
    var uShort: UShort = 22u

    var int: Int = 1
    var uInt: UInt = 1u

    var long: Long = 1
    var uLong: ULong = 1u

    var float: Float = 1.0f
    var double: Double = 2.0

    // A D feature :D
    var myLongNumber: Long = 1_000_000_000L
    var myOtherLongNumber: Double = 1_000_123.000_123

    // This is neat
    var theStart: Byte = 122
    var theEnd: UByte = theStart.toUByte()


    println(1.0 / 2.0)
    println("hi")

    val binaryBoi = 1000
    println(Integer.toBinaryString(binaryBoi))


    // Booleans
    val truth: Boolean = true
    val falseness: Boolean = false
    val nullBool: Boolean? = null

    println("is truth true? $truth")
    println("is falseness false? $falseness")
    println("nullBool is $nullBool but it is equal to true? ${nullBool == true}")

    // Unicode
    println('\uFF00')

    // Time for strings!

    val myCoolString = "This is a cool string"

    // Can iterate a string by char
    for (char in myCoolString) {
        println(char)
    }
    // Since internally the string is literally just a character array, we can iterate it like a list :D
    myCoolString.forEachIndexed { index,char ->
        println("myCoolString index: $index -> $char")
    }

    // This is definitely one way to do this
    fun capitalizeFirstLetter(inputString: String): String {
        if (inputString.isEmpty()) return inputString
        if (!inputString[0].isLetter()) return inputString
        val charArray = inputString.toCharArray()
        charArray[0] = charArray[0].uppercaseChar()
        return charArray.concatToString()
    }

    // Amazing
    println(capitalizeFirstLetter("test 123"))

    // Okay so now let's do the thing again, but let's functionally break it into more things
    // For example, now let's capitalize the first letter of an entire sentence.
    // Function chaining :D
    fun capitalizeEveryFirstLetter(inputString: String): String {
        val wordList: List<String> = inputString.split(" ")
        val listSize = wordList.size - 1
        val builder: StringBuilder = StringBuilder()

        // Digest it
        wordList.forEachIndexed { index, word ->
            builder.append(capitalizeFirstLetter(word))
            if (index < listSize) {
               builder.append(" ")
            }
        }
        return builder.toString()
    }
    println(capitalizeEveryFirstLetter("this is a test of my cool function ye"))

    // This is somehow possible
    println("test".uppercase())
    println("test".lowercase())
    println("123".toLong())

    // A BOLT ON to the string class
    fun String.parseInt(): Int {
        val temp = this.filter { it.isDigit() }
        return when (temp.isEmpty()) {
            true -> 0
            false -> temp.toInt()
        }
    }
    println("test".parseInt())
    println("test123".parseInt())

    // I made this arguing with warr1024 about the default value of parseInt
    class Person(age: Int, heightInMillimeters: Int, weight: Float) {
        // Maybe they don't have a favorite number?
        var favoriteNumber: Int = Int.MIN_VALUE

        fun favoriteNumberHigher(otherPerson: Person): Boolean {
            // This will throw runtime errors if special care is not taken
            return favoriteNumber > otherPerson.favoriteNumber
        }
    }

    val john = Person(23, 500, 123f).apply {
        favoriteNumber = 55
    }
    val frank = Person(55, 123, -1f)

    println("does john have a higher favorite number than frank? ${john.favoriteNumberHigher(frank)}")

    // Now that argument made me wonder if this was easier in Kotlin than it is in java
    val giveThisManSomeGooble = Command("/giveme blarp:gooble -1")
    println(giveThisManSomeGooble.outputInformationString())
    Command.printCommandsRunCount()
    val giveMeAdminPrivsTrustMeBro = Command("/grantme all")
    println(giveMeAdminPrivsTrustMeBro.outputInformationString())
    Command.printCommandsRunCount()
    println(Command("/ping").outputInformationString())
    // The answer was yes

    // Kimpar gave me this idea, the name is for a reason
    fun dontUseThisInProduction(input: String): Int {
        var accumulator = 0
        input.forEach {thisIsBad ->
            accumulator += thisIsBad.code
        }
        return accumulator
    }

    println(dontUseThisInProduction("hi there i'm floop"))

    // Apparently this just works too
    println("test" + 123 + Command("/yell"))

    // Some more string things because I want them documented in one file
    println("${10f} ${10L} ${10} ${10.0}")
    println("""
        BEGIN MULTILINE ------------------------
        hello dere
        I am a big sentence
        I like to type blah blah blah
        pretty cool i think
        END MULTILINE ------------------------
    """.trimIndent())
    println("""
        BEGIN MULTILINE ------------------------
        |This does some things
        |What does it do?
        | I have no idea :>
        END MULTILINE ------------------------
    """.trimIndent().trimMargin())
    println("""
        BEGIN MULTILINE ------------------------
        >Now I get it
        >I can remove the > character
        >Pretty neat!
        END MULTILINE ------------------------
    """.trimIndent().trimMargin(">"))

}

// Inheritable class
open class Shape

// Class that inherits from inheritable class
class Rectangle(val width: Number, val height: Number): Shape() {
    val area = width.toDouble().times(height.toDouble())
}

// Inlined class
@JvmInline
value class Points(val test: Int)

// Singleton
object SingleBoi {
    const val single = true
}

// Abstract class
abstract class PietMondrian {
    abstract fun doArt()
}

// Implements abstract class
class jordan4ibanez : PietMondrian() {
    private val creatorName = "jordan4ibanez"
    override fun doArt() {
        println("$creatorName doesn't know how to do art :(")
    }
}

class Testing

class Command(val inputString: String) {
    var isValid: Boolean = false
        private set
    private var internalElements: MutableList<String> = mutableListOf()
    // This should realistically be a class, scanning through available command families in a map
    var family: String = ""
        private set
    var arguments: MutableList<String> = mutableListOf()
        private set

    init {
        commandsRunCount++
        parseValidity()
        if (isValid) {
            parseElements()
        }
        if (isValid) {
            parseCommandFamily()
            parseCommandArguments()
        }
    }
    private fun parseValidity() {
        isValid = inputString[0] == '/'
    }
    private fun parseElements() {
        internalElements = inputString.split(" ").toMutableList()
        if (internalElements.isEmpty()) {
            isValid = false
        }
        removeForwardSlash()
    }
    // There's probably a better way to do this
    private fun removeForwardSlash() {
        val builder = StringBuilder()
        internalElements[0].forEachIndexed { index, char ->
            if (index > 0) builder.append(char)
        }
        internalElements[0] = builder.toString()
    }
    private fun parseCommandFamily() {
        family = internalElements[0]
    }

    private fun parseCommandArguments() {
        if (internalElements.size == 1) return
        for (i in 1 until internalElements.size) {
            arguments.add(internalElements[i])
        }
    }

    fun outputInformationString(): String {
        if (!isValid) {
            return "INVALID COMMAND!"
        }
        return "command family: $family\n" +
                "command arguments: $arguments"
    }

    companion object {
        var commandsRunCount = 0
            private set
        fun printCommandsRunCount() {
            val pluralization = when (commandsRunCount) {
                1 -> "command"
                else -> "commands"
            }
            println("Game has ran $commandsRunCount $pluralization during runtime.")
        }
    }
}