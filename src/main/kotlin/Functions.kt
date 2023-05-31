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

    manyInput("hi", "there", "this", "is", "a", "test")

    // Very readable, custom syntax, so neat
    val isOneEqualToTwo = 1 compare 2

    // This feels like BASIC
    val test = (1 compare 2) then stage1@{
        println("custom syntax result: $it")
        return@stage1 if (it as Boolean) 1 else 0
    } then stage2@{
        when (it == 1) {
            true -> println("Yeah m8")
            false -> println("Nah m8")
        }
        println("$it next stage")

        return@stage2 "How is this even possible?"
    } with Pair(" 123") stage3@{ thenOutput, pairInput ->
        if (thenOutput !is String || pairInput !is String) return@stage3 Nothing()
        return@stage3 thenOutput + pairInput
    }

    println("The result of this experiment is: ($test)")

    // I somehow made Lua syntax worse! Hooray
    (true) then {
        println("It's true!")
    } end m

    timeForAnotherStupidExperiment()
}

// I wanted to make this look like lua for no reason
object m

// You could probably turn this language into Lisp or some sort of Haskell thing
infix fun Any.then(function: (Any) -> Any) = function(this)
infix fun Any.with(functionData: Pair<Any, (Any, Any) -> Any>) = functionData.second(this, functionData.first)

infix fun Any.end(m: m){}

// That's so sick
infix fun Any.compare(any: Any): Boolean {
    return this == any
}

fun manyInput(vararg input: String) {
    input.forEachIndexed { key,value ->
        println("$key -> $value")
    }
}

fun foo(
    calculator: (Int) -> Int
): Int {
    return calculator(5) + 123
}
