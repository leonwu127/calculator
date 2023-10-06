package leon.home.jagex.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HexadecimalCalculatorUTest {

    HexadecimalCalculator calculator = new HexadecimalCalculator();

    @Test
    public void simpleHexadecimalAddition_ReturnsCorrectResult() {
        // given
        String expression = "0x1F + 0x2D";
        String expected = "0x4C";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void simpleHexadecimalSubtraction_ReturnsCorrectResult() {
        // given
        String expression = "0x2D - 0x1F";
        String expected = "0xE";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void simpleHexadecimalMultiplication_ReturnsCorrectResult() {
        // given
        String expression = "0x2 + 3 * 0x4";
        String expected = "0xE";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void simpleHexadecimalWithParenthesis_ReturnsCorrectResult() {
        // given
        String expression = "(0x2 + 3) * 0x4";
        String expected = "0x14";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void nestedParenthesesWithHex_ReturnsHexValue() {
        // given
        String expression = "((2 + 3) * 2) + 0x14";
        String expected = "0x1E";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);  // Assuming hexadecimal output
    }

    @Test
    public void negativeHexExpression_ReturnsCorrectResult() {
        // given
        String expression = "-0x2D + 0x1F";
        String expected = "-0xE";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void zeroDivisor_ThrowsException() {
        // given
        String expression = "0x5A / 0";
        String expectedMsg = "/ by zero";

        // when
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.calculate(expression);
        });
        // then
        assertEquals(expectedMsg, exception.getMessage());
    }


}