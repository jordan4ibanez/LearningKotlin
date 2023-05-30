import java.lang.Thread.sleep
import kotlin.random.Random

fun inlineClasses() {

    val myCoolPassword = Password("definitely don't do this")

    // I started watching this video https://youtu.be/jhQgHW2VI0o so I decided to try to do it in Kotlin
    // for(i in 0 until 10_000)if(Random.nextBoolean()) print("/")else print("\\")
    

}




@JvmInline
value class Password(private val s: String)

@JvmInline
value class Robot(private val good: Boolean)

