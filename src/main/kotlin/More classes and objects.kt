import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

fun evenMoreThingsAHHH() {

    copyAddress(Address(
        name = "Holmes, Sherlock",
        street = "Baker",
        city = "London",
        state = "United Kingdom",
        zip = "123456"
    )).also(::println)

    val myCoolRectangle = GettersAndSetters(
        width = 5,
        height = 2
    )
    myCoolRectangle.area
        .also(::println)
        .also {
            println("width: ${myCoolRectangle.width} | height: ${myCoolRectangle.height}")
    }

    // Gets a nice compiler warning
    // dontUseThis()


    // This errors out
    // LateInitialization().blah.also { println("$it not initialized")}

    // This doesn't
    LateInitialization().setUp().blah.also { println("$it initialized") }

    // Testing functional interface
    val john = Person2 (
        talker = {
            println("Hi, I'm John!")
        },
        mather = { i ->
            // isEven, automatic return boolean
            i != 0 && i % 2 == 0
        }
    )


    with (john) {
        speak()
        // Apparently you can just define functions here too??
        fun isOrNot(input: Boolean): String {
            return if (input) "it's" else "it's not"
        }
        doMath(45).also { println("${boolToSentenceModifier(it)}, ${isOrNot(it)} even") }
    }


}

// Okay so I got to the part with SAM interfaces, so I want to experiment with it here

// -- Begin experiment --

fun interface Talk {
    fun run()
}

// This is straight out of the tutorial
fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

class Person2(private val talker: Talk, private val mather: IntPredicate) {
    fun speak() {
        talker.run()
    }

    fun doMath(input: Int): Boolean = mather.accept(input)
}


// -- End experiment --

class LateInitialization {

    // Wrapper a number in an integer
    lateinit var blah: Number

    fun setUp(): LateInitialization {
        blah = 55
        return this
    }
}

const val DEPRECATED = "This function is deprecated!"

// That's pretty neat :D
@Deprecated(DEPRECATED) fun dontUseThis(): Int {
    return 314
}

// Pretend this is called "rectangle"
class GettersAndSetters(val width: Int, val height: Int) {
    val area: Int
        get() = width * height
}

// This is a heavily modified version of the example to jump ahead in the examples
// Just because I wanted to basically
data class Address(
    var name: String = "",
    var street: String = "",
    var city: String = "",
    var state: String = "",
    var zip: String = ""
)


// I saw the "Tuple Return" section here https://gi-no.github.io/kotlin-is-like-typescript/,so I wanted to see if there was a better way?
fun tupleReturn(): Array<Double> {
    return arrayOf(3.59, 3.69, 3.79)
}
// This one is the silly version, might as well do toArray()
fun tupleReturn(vararg prices: Double): Array<Double> {
    val gasPrices = Array<Double>(prices.size){0.0}
    prices.forEachIndexed{ key,value ->
        gasPrices[key] = value
    }
    return gasPrices
}
// Now this one is the internal version
fun tupleReturnYe(): Triple<Any, Any, Any> {
    // You can just shove anything in there
    return Triple("hi", 343, Address())
}
// Let's make a 3x3 tuple monstrosity which is nonuple. But you should probably just use an Array of Any :P
fun tupleReturnMonstrosity(): Triple<Triple<Any, Any, Any>, Triple<Any, Any, Any>, Triple<Any, Any, Any>> {
    return Triple(Triple(Nothing(), 2134, "wow"), Triple(Address(), "flops", "234"), Triple(1,2, 3))
}

// This could be input: Any | output: Any
fun copyAddress(address: Address): Address {

    // This is the raw Kotlin version of it without reflection, very rigid, but supports non-data class
//    return Address(
//        name = address.name,
//        street = address.street,
//        city = address.city,
//        state = address.state,
//        zip = address.zip
//    )


    // This portion is slightly modified from studying this https://stackoverflow.com/questions/49511098/call-data-class-copy-via-reflection
    Address::class.memberFunctions.first {
        println(it)
        it.name == "copy"
    }.also {
        // This portion could be INLINED :D See below
        // val instanceParameters: KParameter = it.instanceParameter ?: throw RuntimeException("ERROR: Failed to get instance parameter!")
        // This is creating a raw generic Object, then being cast into object type
        // return it.callBy(mapOf(instanceParameters to address)) as Address

        // You could write it like so:
        return it.callBy(mapOf(it.instanceParameter!! to address)) as Address
        // (I liked the inlined version better)
    }
}