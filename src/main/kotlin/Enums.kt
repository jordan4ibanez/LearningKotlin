fun enums() {

    println("Begin Kotlin clean enumerator implementation")
    // Actual Kotlin implementation
    Color.values().forEachIndexed { index, color ->
        println("$index -> $color -> ${color.rgb}")
    }

    println("Begin my horrible way to show what is kind of happening behind the scenes so you don't have to pull your hair out to deal with any of this")
    // Verbose, grossly deconstructed manual implementation of an enumerator
    Colors.values().forEachIndexed { index,color ->
        println("$index -> ${color.name} -> ${color.rgb}")
    }



}

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Color (val rgb: Int) {
    // These are self-defining static variables internally!
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

// So an enum I suppose would look like this if deconstructed, and absolutely cumbersome to work with
class VerboseColor(val rgb: Int, val name: String)

class Colors {
    companion object {
        val RED = VerboseColor(0xFF0000, "RED")
        val GREEN = VerboseColor(0x00FF00, "GREEN")
        val BLUE = VerboseColor(0x0000FF, "BLUE")

        fun values(): Array<VerboseColor> {
            return arrayOf(RED, GREEN, BLUE)
        }
    }
}


