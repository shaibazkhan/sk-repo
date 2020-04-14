package samples

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MainTest {
    private val main = Main()

    @Test
    fun `say hello to world`() {
        assertEquals("Hello World!", main.sayHelloTo())
    }

    @Test
    fun `say hello to person`() {
        assertEquals("Hello Shaibaz!", main.sayHelloTo("Shaibaz"))
    }
}