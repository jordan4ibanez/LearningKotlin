import kotlin.reflect.KParameter
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

fun evenMoreThingsAHHH() {
    copyAddress(Address().apply {
        name = "Holmes, Sherlock"
        street = "Baker"
        city = "London"
        state = "United Kingdom"
        zip = "123456"
    }).also(::println)
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

fun copyAddress(address: Address): Address {
    // This portion is slightly modified from studying this https://stackoverflow.com/questions/49511098/call-data-class-copy-via-reflection
    Address::class.memberFunctions.first {
        println(it)
        it.name == "copy"
    }.also {
        // This portion could be INLINED :D See below
        val instanceParameters: KParameter = it.instanceParameter ?: throw RuntimeException("ERROR: Failed to get instance parameter!")
        // This is creating a raw generic Object, then being cast into object type
        return it.callBy(mapOf(instanceParameters to address)) as Address

        // You could write it like so:
        // return it.callBy(mapOf(it.instanceParameter!! to address)) as Address
    }
}