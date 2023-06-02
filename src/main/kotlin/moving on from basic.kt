fun noMoreBasicPlz() {

    // A very small example of delegate functions
    val test = CoolerBeans {
        println("Your number is 5")
    }
    test.cool()


    val printy: NumberPrinter = {
        println("123")
    }

    printy()

    val anotherTest = CoolerBeans(printy)

    anotherTest.cool()

    // Another way to instantiate that syntax
    val beans = CoolerBeans({println(5)})

    // Example as if I were utilizing this to create custom mobs or something.
    // This feels kind of like Lua syntax
    val zombie = Mob (
        onStep = {self ->
            self.memory += 1
        },
        debug = {self ->
            println("I have ${self.memory}")
        }
    )

    (1..10).forEach{ _ -> zombie.runLogic()}


}

// Example 1: 0 argument delegate function
typealias NumberPrinter = () -> Unit

class CoolerBeans(val cool: NumberPrinter)

// Example 2: 2 argument delegate function, can be used for mobs, entities, etc

typealias OnStep = (Mob) -> Unit
typealias Debug = (Mob) -> Unit
class Mob(val onStep: OnStep, val debug: Debug) {

    var memory = 0

    fun runLogic() {
        // delta blah blah
        onStep(this)
        debug(this)
    }
}

