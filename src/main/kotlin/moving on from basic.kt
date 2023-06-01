fun noMoreBasicPlz() {

    val test = CoolerBeans {
        println("Your number is 5")
    }
    test.cool()
}

typealias NumberPrinter = () -> Unit

class CoolerBeans(val cool: NumberPrinter) {
}