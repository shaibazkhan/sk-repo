package samples

import java.lang.IllegalArgumentException

class StringCalculator {
    fun add(input: String = ""): String {
        if (input.isEmpty())
            return "0"

        val numbers = input.split(",", "\n")
        if (numbers.last().toIntOrNull() == null)
            throw IllegalArgumentException("Number expected but EOF found")

        return input.split(",", "\n").sumBy { it.toInt() }.toString()
    }
}