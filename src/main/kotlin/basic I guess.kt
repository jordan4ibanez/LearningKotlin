fun timeForAnotherStupidExperiment() {
    // I'mma call it jBASIC, I'm sure that'll catch on
    val basic = BASIC("""
        10 assign x 0
        20 print this_is_very_BASIC
        30 print var x
        40 add x 1
        // If it is equal to 10 loops exit the program
        50 equals x 10 break 
        60 goto 20
    """.trimIndent()
    )

    basic.run()

    // That is the most basic BASIC basically

    // Onto the next

    noMoreBasicPlz()
}


class BASIC(sourceCode: String) {

    // This only works with numbers because I don't feel like figuring everything out for the sake of an experiment
    private val varMap = HashMap<String, Int>()

    private val funMap = HashMap<Int,List<String>>()

    private var nextLine = -1

    private var running = true

    private val lineOrder = mutableListOf<Int>()

    init {
        var currentLine = -1

        sourceCode.split('\n').forEach { line ->
            val temp = line.split(' ').toMutableList()
            val lineNumber = try {
                temp.removeAt(0).toInt()
            } catch (e: Exception) {
                -1
            }
            if (lineNumber == -1) return@forEach

            if (lineNumber < currentLine) throw RuntimeException("BASIC ERROR: Line numbers must be in order.")
            currentLine = lineNumber

            // Start off the logic code at the first line
            if (nextLine == -1) nextLine = lineNumber

            lineOrder.add(lineOrder.size, lineNumber)

            val builder = StringBuilder()

            temp.forEachIndexed { key,value ->
                builder.append(value)
                if (key < temp.size - 1) {
                    builder.append(" ")
                }
            }

            funMap[currentLine] = temp.toList()
        }
    }

    fun run() {
        while(running) {
            interpreter()
        }
    }

    fun isOp(input: String): Boolean = when (input) {
        "assign", "add", "equals", "print" -> true
        else -> false
    }

    fun doOp(call: String, variable: String, value: String): Boolean {
        when(call) {
            "assign" -> varMap[variable] = value.toInt()
            "add" -> varMap[variable] = varMap[variable]!! + value.toInt()
            "equals" -> {
                return varMap[variable] == value.toInt()
            }
            "print" -> when (variable) {
                "var" -> println(varMap[value])
                else -> println(variable)
            }

        }
        return false
    }

    private fun interpreter() {
        // Current line code
        val clc = funMap[nextLine] ?: throw RuntimeException("BASIC ERROR: Tried to GOTO invalid line.")

        // Very basic boolean memory for logic flow
        var memory = true

        clc.forEachIndexed { i, word ->
            when {
                word == "//" -> return
                // No safety check for now
                isOp(word) -> {
                    memory = doOp(word, clc[i + 1], if (clc.size > 2) clc[ i + 2] else "")
                }
                // Basic goto implementation
                word == "goto" -> {
                    nextLine = clc[i + 1].toInt()
                    return
                }
                word == "break" -> {
                    if (memory) running = false
                }
            }
        }

        lineOrder.forEach {
            if (it > nextLine) {
                nextLine = it
                return
            }
        }
    }
}