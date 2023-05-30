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
    

}

interface What {
    fun blah()
}