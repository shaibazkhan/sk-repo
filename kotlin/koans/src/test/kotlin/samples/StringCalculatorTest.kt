package samples

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class StringCalculatorTest {
    private val calculator = StringCalculator()

    @Test
    fun `default param is equal to zero`() {
        assertEquals("0", calculator.add())
    }

    @Test
    fun `empty string is equal to zero`() {
        assertEquals("0", calculator.add(""))
    }

    @Test
    fun `add single digit`() {
        assertEquals("1", calculator.add("1"))
    }

    @Test
    fun `add two numbers with "," separator`() {
        assertEquals("3", calculator.add("1,2"))
    }

    @Test
    fun `add numbers with line separator between them separated by ","`() {
        assertEquals("6", calculator.add("1\n2,3"))
    }

    @Test
    fun `input shouldn't end in a separator`() {
        val error = assertThrows(IllegalArgumentException::class.java) {
            calculator.add("1,3,")
        }
        assertEquals("Number expected but EOF found", error.message)
    }

    @Test
    fun `add numbers with different separator`(){
        assertEquals("3", calculator.add("//;\n1;2"))
    }

    @Test
    fun `negative numbers results in error`() {
        val error = assertThrows(IllegalArgumentException::class.java) {
            calculator.add("-1,-2")
        }
        assertEquals("Negative not allowed : -1,-2", error.message)
    }

    @Test
    fun `multiple errors will return all error messages`() {
        val error = assertThrows(IllegalArgumentException::class.java) {
            calculator.add("1,-3,")
        }
        assertEquals("Number expected but EOF found\nNegative not allowed : -3", error.message)
    }
}