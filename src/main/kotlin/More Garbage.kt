fun beginMoreGarbage() {

    // Anonymous object
    // I have no idea how tf they figured this one out on the JVM
    val mysteryBoi = object {
        val blah = "Blah"
        fun scream() = println(blah)
    }

    mysteryBoi.scream()

    



}