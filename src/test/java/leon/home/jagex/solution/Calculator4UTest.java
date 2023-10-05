package leon.home.jagex.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Calculator4UTest {

    @Test
    public void simpleHexadecimalAddition_ReturnsCorrectResult() {
        // given
        Calculator4 calculator = new Calculator4();

        // when
        String result = calculator.calculate("1F+ 2D");

        // then
        assertEquals("4C", result);
    }

    @Test
    public void simpleHexadecimalSubtraction_ReturnsCorrectResult() {
        // given
        Calculator4 calculator = new Calculator4();

        // when
        String result = calculator.calculate("2D - 1F");

        // then
        assertEquals("E", result);
    }

    @Test
    public void multipleOperatorsWithoutParenthesesWithHexadecimal_ReturnsCorrectResult() {
        // given
        Calculator4 calculator = new Calculator4();

        // when
        String result = calculator.calculate("2 + 3 * 4");

        // then
        assertEquals("14", result);  // Assuming operator precedence is handled correctly
    }

    @Test
    public void multipleOperatorsWithParentheses_ReturnsCorrectResult() {
        // given
        Calculator4 calculator = new Calculator4();

        // when
        String result = calculator.calculate("(2 + 3) * 4A");

        // then
        assertEquals("172", result);  // Assuming hexadecimal output
    }

    @Test
    public void nestedParenthesesWithDecimal_ReturnsDecimalValue() {
        // given
        Calculator4 calculator = new Calculator4();

        // when
        String result = calculator.calculate("((2 + 3) * 2) + 4");

        // then
        assertEquals("14", result);  // Assuming hexadecimal output
    }

    @Test
    public void negativeResult_ReturnsCorrectResult() {
        // given
        Calculator4 calculator = new Calculator4();

        // when
        String result = calculator.calculate("-5 + 3");

        // then
        assertEquals("FFFFFFFE", result);
    }

}