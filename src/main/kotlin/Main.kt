import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.Exception
import kotlin.collections.HashMap
import kotlin.random.Random

const val blah: String = "good day lad!"

fun sum(a: Number, b: Number) = a.toDouble().plus(b.toDouble()) as Number

fun doesNothing(a: Any) = println(a)

// Main was created when I literally had no idea what I was doing, so that's why it looks so weird.
// I am keeping it like this to document the learning journey.
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
//    println("What is your name: ")
//    val userName = readln()
//
//    println("On a scale of 1-3 how are you feeling: ")
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

    // This is pretty meta
    println("test123 is of length: ${"test123".length}")

    println("""
        $9.99
    """.trimIndent())
    // It says to do ${'$'}_9.99, but I do not know why?
    val price = """
       $9.99 
    """.trimIndent()
    println(price)


    // Time for arrays, the building blocks of a program! Woo

    // the raw way
    val interestingArray = Array(5){ w -> (w * w).toString() }
    interestingArray.forEachIndexed { key,value -> println("interesting array: $key -> $value") }

    // You could also one line that like this
    Array(5){ w -> (w * w).toString() }.forEachIndexed { key,value -> println("interesting array inlined: $key -> $value") }

    // Initialized to 0
    val intArray: IntArray = IntArray(5)
    intArray.forEach { assert(it == 0) }
    println("all values in intArray are 0!")

    // Initialized to 123
    val intArray2: IntArray = IntArray(5){ 123 }
    intArray2.forEach { assert(it == 123) }
    println("all values in intArray2 are 123!")

    // Initialize numerically with 0 count! so: 0,1,2,3,4
    val intArray3: IntArray = IntArray(5){ it }
    intArray3.forEachIndexed {key,value -> assert(key == value)}
    println("all values in intArray3 are 0 counted!")

    // Now it's going to get a bit complicated so this will be moved into a new function scope :D
    moreLearning()
}

fun moreLearning() {

    // Begin types

    // You can wrapper an object as a package :D
    val test: Any = 545
    println(test.javaClass)

    fun cool(blah: Int) {
        println(blah)
    }

    // This doesn't work with it as any
    // cool(test)

    // This one does
    cool(test as Int)

    // Implicit auto cast
    println(test + 0.5f)

    fun floaty(blah: Float) {
        println("That's floaty! $blah")
    }

    // Explicit cast
    floaty(test.toFloat())

    // Working with raw Number object
    val rawBoi: Number = 54

    fun getThatType(input: Any) {
        when (input) {
            is Long -> println("Long boi")
            is Int -> println("Integral boi")
            is Double -> println("Double floaty boi")
            is Float -> println("Floaty boi")
            else -> println("That's not a number :O")
        }
    }
    getThatType(rawBoi)
    getThatType(54L)
    getThatType(54.4f)
    getThatType(54.5)
    getThatType("hi there")

    // Different ways to work with the string

    val myString = "hi I'm a string"

    val myCharSequence = myString.asSequence()
    val myCharArray = myString.toCharArray()
    val myCharIntStream = myString.chars()
    // You can get the string as integers!
    myCharIntStream.forEach {
        println("$it")
    }

    // Smart casting is so sick
    fun multiInterop(input: Any) {
        when (input) {
            is String -> {
                println("String length is: ${input.length}")
            }
            is Int -> {
                println("$input plus 4 is: ${input + 4}")
            }
            // No else branch needed :D
        }
    }

    multiInterop("Hello I am a string")
    multiInterop(5555)
    multiInterop(HashMap<String, Int>(0))

    // Skipping ahead a bit to try something here with hashmaps

    // This is the one to use, the kotlin defined one
    val kotlinHashMap = hashMapOf<String, Int>()

    // This just gives you a java hashmap
    // I think this is designed like this for interop with java code
    // val javaHashMap = HashMap<String, Int>()

    // This feels kind of like lua - Assignment
    kotlinHashMap["test"] = 55
    kotlinHashMap["hi there"] = 123

    // Now I'm looking this up before getting to the hashmap section so this might be wrong?
    if ("test1" in kotlinHashMap) {
        println("test1 is in there!")
    } else {
        println("test1 was not in there")
    }

    // Safety check inline - Prevents a crash
    val gotIt = kotlinHashMap["test1"] ?: "nope"

    println("BEGIN INTEROP INTO HASHMAP ----------")

    // We can throw it into the function above
    multiInterop(gotIt)

    // Now let's inline this to get a known value
    multiInterop(kotlinHashMap["test"] ?: "failure")


    // A final test out of the scope of learning this section which can auto assign a function to do things with syntactic sugar
    fun blah(input: Boolean) = when(input) {
        true -> {
            println("Yeah that's pretty true")
        }
        else -> {
            println("Nah that's not true at all")
        }
    }

    blah(true)
    blah(false)

    // Now back to smart casts

    // Type blocking function
    fun onlyAcceptStrings(input: Any) {
        if (input !is String || input.length == 0) {
            println("NOT A STRING!")
            return
        }
        println("Got a string, it's length is: ${input.length}")
    }

    onlyAcceptStrings(123)
    onlyAcceptStrings(false)
    onlyAcceptStrings("hi there")


    // A mini test of Rust style handling
    open class Result(val data: Any = false, val existence: Boolean) {
        // Chainable
        fun isSome(): Result? {
            if (!existence) return null
            return this
        }
        fun unwrap(): Any {
            if (!existence) throw RuntimeException("Failed to unwrap None!")
            return data
        }
    }
    class Some(input: Any) : Result(input, true)

    // Now this is useful to the EXTREME!
    class None : Result(existence =  false)

    val nullValue = if (Random.nextBoolean()) true else null

    val myCoolResult = nullValue?.let { Some(it) } ?: None()

    println(myCoolResult.isSome()?.unwrap())

    // Unsafe casts
    val y = 4

    // Fails
    // val x: String = y as String

    // Fails
    // val x: String? = y as String?

    // X turns into null
    val x: String? = y as? String

    println(x)

    // Chaining yet another function into a new scope
    evenMoreLearning()
}

fun evenMoreLearning() {

    // Onto control flow we go

    // Conditions and loops

    val a = Random.nextInt(123)
    val b = Random.nextInt(123)

    var max = a

    // One line assignment
    if (a < b) max = b

    // As an else statement
    if (a > b) {
        max = a
    } else {
        max = b
    }

    // As an expression
    max = if (a > b) a else b

    // This one's going to take a bit to get used to
    val maxLimit = 1
    val maxOrLimit = if (maxLimit > a) maxLimit else if (a > b) a else b

    val max2 = if (a > b) {
        println("Chose A")
        a
    } else {
        println("Chose B")
        b
    }
    println("Max is $max")
    println("Max or limit is $maxOrLimit")
    println("Max2 is $max2")

    // When expression
    val x = 100 > Random.nextInt(1000)

    when (x) {
        true -> println("X was true")
        false -> println("X was false")
        // Not necessary
        else -> println("X was somehow something else")
    }

    // An interesting way to do comparators
    val cool = Random.nextInt(100) - 50

    cool.let {
        when {
            it > 0 -> println("cool is positive! $it")
            it < 0 -> println("cool is negative! $it")
            // Intellij is so smart it knows that this branch is always zero :D
            else -> println("cool is zero! $it")
        }
    }

    // Let's do this again as a one-liner. Shows where semicolons can be used in Kotlin!
    (Random.nextInt(100) - 50).let { when {it > 0 -> println("positive! $it"); it < 0 -> println("negative! $it"); else -> "Zero! $it"} }

    // This can automatically deduce types :O

    fun parseIt(input: Number) {
        when (input) {
            input.toInt() -> println("It's an integer!")
            else -> println("It's something else")
        }
    }

    parseIt(4324)
    parseIt(234.4)

    // Can wrapper that further
    fun parseANY(input: Any) {
        if (input !is Number) {
            println("Whoa there bud, that's not a number, that's ${input.javaClass}")
            return
        }
        parseIt(input)
    }

    parseANY("hi there")
    parseANY(234)
    parseANY(234.234f)

    // Double depth logic route
    for (i in 0 until 100) {
        println("$i is " +
            when (i > 0) {
                false -> "zero!"
                true -> {
                    when (i % 2 == 0) {
                        true -> "even!"
                        false -> "odd!"
                    }
                }
            }
        )
    }

    // Range checks
    (0 until 21).forEach {
        when (it) {
            in 1..10 -> println("1 to ten $it")
            !in 1..20 -> println("out of bounds $it")
            else -> println("none of the conditions $it")
        }
    }

    // Ultra generic string consumer modifier - Will return anything else as itself unmodified
    fun processString(input: Any): Any = when(input) {
        is String -> input.plus(" hi there")
        else -> {
            println("FAILED STRING PROCESSING")
            input
        }
    }
    println(processString("jordan"))
    println(processString(2134))
    println(processString(false))

    // Taking a break to experiment with learned features

    // Let's bolt on a little feature
    fun Int.isOdd(): Boolean {
        return this != 0 && this % 2 == 0
    }
    // Utilizing the one-liner functions
    // This has been moved to package scope below
    // fun boolToSentenceModifier(truth: Boolean): String = if (truth) "yes" else "no"

    // And do the same thing as above, but simpler
    (0..100).forEach {
        println("$it is even? ${boolToSentenceModifier(it.isOdd())}")
    }

    // A cascading functional style
    IntArray(10) { it }.forEachIndexed { key,value ->
        println("$key -> $value")
    }

    // One liner styles
    val myCoolIntArray = IntArray(10) { it * 100 }
    
    for (item in myCoolIntArray) println(item)
    
    // More explicit
    for (item: Int in myCoolIntArray) println(item)

    // Functional
    myCoolIntArray.forEach { println(it) }

    // Ranges again
    for (i in 1..3) println(i)

    // 6, 4, 2, 0
    for (i in 6 downTo 0 step 2) println(i)

    // Count by 5
    for (i: Int in 0..100 step 5) println(i)

    // Count by 5 but this will miss 100, so it will count UNTIL 100 is reached
    for (i: Int in 0 until 100 step 5) println(i)

    // Count up and down 1 to 10 10 to 1 10 times
    /* This would look kind of like this on a 2d graph
     /\  /\  /\  /\  /\
    /  \/  \/  \/  \/  \
     */
    for (unused in 1..10) {
        // Using that function bolt on from before :)
        when (unused.isOdd()) {
            false -> (0..10).forEach { println("$it up we gooo!") }
            true -> (10 downTo 0).forEach{ println("$it down we gooo!") }
        }
    }

    // Another sidetrack, experimenting with
    val myHashMap = hashMapOf<String, Number>(
        "test" to 123,
        "pi" to 3.14,
        "life" to 42L
    )

    for (keyValue in myHashMap) {
        println("${keyValue.key} -> ${keyValue.value}")
    }

    myHashMap.forEach { key,value ->
        println("$key -> $value")
    }

    for ((key,value) in myHashMap) {
        println("$key -> $value")
    }

    println("I got this for flarp: ${myHashMap.getOrDefault("flarp", 0)}")

    // Starting a new scope so this doesn't affect things down the line
    run {
        var w = 10
        while (w >= 0) {
            println("w is $w")
            w--
        }
    }


    run {
        class Person(val name: String?)
        val john = Person(name = null)
        // A mini jump
        val s = john.name ?: "oops"
        println("John's name is $s")
    }

    // Breaking a loop
    loop@ for (i in 0..100) {
        if (i > 50) break@loop
    }

    // Break labels
    outer@ for (i in 0..100) {
        inner@ for (w in 0..100) {
            if (w > 20) break@inner
        }
        if (i > 30) break@outer
    }


    // Functional function flow

    // Style 1
    run {
        listOf(1,2,3,4,5).forEach {
            // This works as a function "continue"
            if (it == 3) return@forEach
            println("Loopy $it")
        }
        println("I'm still running woot!")
    }

    // Style 2
    run {
        // Function is a first class citizen :)
        listOf(1,2,3,4,5).forEach(fun(value: Int) {
            // This works as a functional "continue"
            if (value == 3) return
            println("Loopy $value")
        })
        println("I'm still running woot!")
    }

    // Simulation of break in functional flow
    // Run is just so I don't have to call this as a function
    run {
        run loop@ {
            listOf(1, 2, 3, 4, 5).forEach {
                // Break
                if (it == 3) return@loop
                println("Loopy $it")
            }
        }
        println("I'm still running again yay!")
    }

    // This one is a bit of an experiment, functional continue loop break.
    // This is a purely speculative problem that I can foresee myself having.
    // Might have to run this a few times to get a non-escaping procedure.
    val failed = run {
        // Set a purely arbitrary accumulator limitation
        val limiter = 600

        // Set scoped vars
        var accumualator = 0
        val list = mutableListOf<String>()

        // Aggregate a random hashmap
        val map = hashMapOf<String, Int>()
        (0..10).forEach { _ ->
            map[UUID.randomUUID().toString()] = Random.nextInt(120)
        }

        // Now iterate and utilize control flow labels
        map.forEach { (key, value) ->
            if (value > 500) return@forEach
            accumualator += value
            list.add(key)

            if (accumualator > limiter) {
                println("bailing out of loop accumulator has hit over $limiter! It is at $accumualator")
                // Additional debugging output (if this fails)
                return@run true
            }
        }
        println("Final accumulation: $accumualator")
        println("Map: $map")

        // Additional debugging output (if this fails)
        return@run false
    }
    println("The random function failed? $failed")

    // Into the next scope we goooo
    moreStuffWoo()
}

fun moreStuffWoo() {

    // Time for exceptions

    // Try block
    try {
        throw Exception("I am an exception, yay")
    } catch (e: Exception) {
        println("Caught an exception! ($e)")
    } finally {
        println("we did it bois")
    }

    // Try expression
    val isThisGood: Int? = try { "blah".toInt() } catch (e: Exception) { null }
    println("well, was it good? $isThisGood")


    // Try block assignment
    val test: Int? = if (Random.nextBoolean()) null else 1

    val blah2 = try {
        test ?: throw RuntimeException("oh no")
    } catch (e: Exception) {
        3
    }

    println("blah2 is $blah2")

    // Nothing is also a type, so it can be used in expressions
    fun breakIt(): Nothing {
        throw IllegalAccessError("you done goofed now boi")
    }
    // Commented out for a reason :P
    // breakIt()

    // You can use it like this!
    // val oof = null ?: breakIt()

    val b = null
    println("b is type Nothing? : ${b is Nothing?}")

    // Interesting experiment
    val blah3 = if (b as? Int != null) b as? Int else 0
    println(blah3)

    // This file was over 1_000 lines long at this point so part2 is in Classes and Objects.kt
    part2()
}

fun boolToSentenceModifier(truth: Boolean): String = if (truth) "yes" else "no"

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

class Command(private val inputString: String) {
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