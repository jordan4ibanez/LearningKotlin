fun beginMoreGarbage() {

    // Anonymous object
    // I have no idea how tf they figured this one out on the JVM
    val mysteryBoi = object {
        val blah = "Blah"
        fun scream() = println(blah)
    }

    mysteryBoi.scream()

    // This portion is probably going to NEVER be used anywhere in my programs but I'm gonna try it anyways

    abstract class WhatEvenHappensHere {
        abstract fun test()
    }

    fun manufactureAnObject(): WhatEvenHappensHere {
        // See next portion for the actual useful version
        return object : WhatEvenHappensHere() {
            override fun test() = println("hi")
        }
    }

    val manufacturer = manufactureAnObject()

    manufacturer.test()

    // Next portion

    // O nuu we can't do this
    // val blarf = What()

    // So I guess we can do this
    // I suppose this would be a local factory function
    fun thingsAreGettingComplicated(): What {
        // This part I can see being kinda useful
        return object : What {
            override fun blah() {
                println("blah")
            }
        }
    }

    // Or this
    val blarf = object : What {
        override fun blah() {
            println("MORE BLAH!")
        }
    }

    val blarf2 = thingsAreGettingComplicated()

    blarf.blah()
    blarf2.blah()

    for (i in 1..10) {
        SingleTonTheThird.update()
    }
    SingleTonTheThird.poll().also { SingleTonTheThird.yell(it) }

    // This is a static method
    StaticBoi.blah()
    // This too
    StaticBoi.moreBlah()

}


// This is jvm equivalent to having a class with all static components
class StaticBoi private constructor() {
    companion object {
        @JvmStatic
        fun blah() = println("I'm static woo")
        @JvmStatic
        fun moreBlah() = println("hi there")
    }
}

object SingleTonTheThird {
    private const val x = 5
    private var y = 0
    fun update() {
        y += x
    }
    fun poll(): Int {
        return y
    }
    fun yell(input: Int) {
        println("Yes I contain the number $y you gave me $input")
    }
}

interface What {
    fun blah()
}