package samples

import java.lang.IllegalArgumentException

class StringCalculator {
    fun add(input: String = "") = if(input.isNotEmpty()) calculate(numbersOnly(extract(input))) else "0"

    private fun calculate(numbers: List<String>): String {
        numbers.validateBeforeCalculate()
        return numbers.sumBy { it.toInt() }.toString()
    }

    private fun numbersOnly(extracted: Pair<List<String>, String>): List<String> {
        val (delimiter, operands) = extracted
        return operands.split(*delimiter.toTypedArray())
    }

    private fun extract(input: String) =
        if (input.startsWith("//"))
            Pair(listOf(input.substringAfter("//").substringBefore("\n")),
                 input.substringAfter("\n"))
        else
            Pair(listOf(",", "\n"), input)

    private fun Iterable<String>.validateBeforeCalculate() {
        fun validateMissingNumberInLastPosition(input: String): String? {
            return input.toIntOrNull()?.let { "" }?: "Number expected but EOF found"
        }

        fun validateNegativeNumbers(): String {
            val negatives = this.filter { it.toIntOrNull() !=null }.filter { it.toInt() < 0 }
            return if (negatives.isNotEmpty())
                "Negative not allowed : ${negatives.joinToString(",")}"
            else
                ""
        }

        val messages = listOfNotNull(
                validateMissingNumberInLastPosition(this.last()),
                validateNegativeNumbers()
        )

        if(messages.any { it.isNotEmpty() })
            throw IllegalArgumentException(messages.filter { it.isNotEmpty() }.joinToString("\n"))
    }
}