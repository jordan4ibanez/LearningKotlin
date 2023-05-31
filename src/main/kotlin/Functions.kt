// This part is ironic, I suppose
fun timeForFunctions(input: String) {
    println(input)

    // shove a lambda into a defined function

    // This is probably why also exists
    println(
        foo {
            it * 123
        }
    )

    foo {
        it * 123
    }.also(::println)

    
    val (x,y,z) = arrayOf("x1", "y2", "z3")

    println("$x,$y,$z")
}

fun foo(
    calculator: (Int) -> Int
): Int {
    return calculator(5) + 123
}
