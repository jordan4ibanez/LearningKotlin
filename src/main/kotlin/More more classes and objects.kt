fun noIdeaWhatToCallThis() {
    val myCoolSentence = mutableListOf("hi", "there")
    myCoolSentence.asSentence().also(::println)
    myCoolSentence.asSentence(", ").also(::println)
    myCoolSentence.swap(0,1)
    myCoolSentence.asSentence(" ").also(::println)
    // Added in defaults, so you can swap it as a pair
    myCoolSentence.swap()
    myCoolSentence.asSentence().also(::println)
    // It's also chainable
    myCoolSentence.swap().asSentence().also(::println)

}

// You can just bolt on stuff to anything, it's a miracle
fun MutableList<String>.asSentence(separator: String = " "): String {
    val builder = StringBuilder()
    val words = this.size
    this.forEachIndexed { key,value ->
        builder.append(value)
        if (key + 1 < words) builder.append(separator)
    }
    return builder.toString()
}
// Pure generics
fun <T> MutableList<T>.swap(index1: Int = 0, index2: Int = 1): MutableList<T> {
    assert(this.size >= 2)
    val temp: T = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
    return this
}

// This part is just copied and pasted from the tutorial with IntelliJ coloring, so it's easier to understand

private fun foo() { } // visible inside example.kt

public var bar: Int = 5 // property is visible everywhere
    private set         // setter is visible only in example.kt

internal val baz = 6    // visible inside the same module

open class Outer2 {
    private val a = 1
    protected open val b = 2
    internal open val c = 3
    val d = 4  // public by default

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer2() {
    // a is not visible
    // b, c and d are visible
    // Nested and e are visible

    override val b = 5   // 'b' is protected
    override val c = 7   // 'c' is internal
}

class Unrelated(o: Outer2) {
    // o.a, o.b are not visible
    // o.c and o.d are visible (same module)
    // Outer.Nested is not visible, and Nested::e is not visible either
}