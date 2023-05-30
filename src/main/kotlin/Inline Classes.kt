

// I dunno why I made this an entire file but here you go
fun inlineClasses() {

    val myCoolPassword = Password("definitely don't do this")

    // I started watching this video https://youtu.be/jhQgHW2VI0o so I decided to try to do it in Kotlin
    // for(i in 0 until 10_000)if(Random.nextBoolean()) print("/")else print("\\")

    // Onto the next, wow this file is totally necessary
    beginMoreGarbage()

}




@JvmInline
value class Password(private val s: String)

@JvmInline
value class Robot(private val good: Boolean)

