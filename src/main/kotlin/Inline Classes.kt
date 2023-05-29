fun inlineClasses() {

    val myCoolPassword = Password("definitely don't do this")



}

@JvmInline
value class Password(private val s: String)

@JvmInline
value class Robot(private val good: Boolean)

