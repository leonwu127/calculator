package leon.home.jagex.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HexadecimalCalculatorTest {

    @Test
    public void simpleHexadecimalAddition_ReturnsCorrectResult() {
        // given
        HexadecimalCalculator calculator = new HexadecimalCalculator();

        // when
        String result = calculator.calculate("0x1F+ 0x2D");

        // then
        assertEquals("0x4C", result);
    }

    @Test
    public void simpleHexadecimalSubtraction_ReturnsCorrectResult() {
        // given
        HexadecimalCalculator calculator = new HexadecimalCalculator();

        // when
        String result = calculator.calculate("0x2D - 0x1F");

        // then
        assertEquals("0xE", result);
    }

    @Test
    public void simpleHexadecimalMultiplication_ReturnsCorrectResult() {
        // given
        HexadecimalCalculator calculator = new HexadecimalCalculator();

        // when
        String result = calculator.calculate("0x2 + 3 * 0x4");

        // then
        assertEquals("0xE", result);  // Assuming operator precedence is handled correctly
    }

    @Test
    public void simpleHexadecimalWithParenthesis_ReturnsCorrectResult() {
        // given
        HexadecimalCalculator calculator = new HexadecimalCalculator();

        // when
        String result = calculator.calculate("(2 + 3) * 0x4A");

        // then
        assertEquals("0x172", result);  // Assuming hexadecimal output
    }

    @Test
    public void nestedParenthesesWithDecimal_ReturnsDecimalValue() {
        // given
        HexadecimalCalculator calculator = new HexadecimalCalculator();

        // when
        String result = calculator.calculate("((2 + 3) * 2) + 0x14");

        // then
        assertEquals("0x1E", result);  // Assuming hexadecimal output
    }

    @Test
    public void negativeResult_ReturnsCorrectResult() {
        // given
        HexadecimalCalculator calculator = new HexadecimalCalculator();

        // when
        String result = calculator.calculate("-0x5A + 3");

        // then
        assertEquals("-0x57", result);
    }

    @Test
    public void zeroDivisor_ThrowsException() {
        // given
        HexadecimalCalculator calculator = new HexadecimalCalculator();
        // when
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.calculate("0x5A / 0");
        });
        // then
        assertEquals("/ by zero", exception.getMessage());
    }


}