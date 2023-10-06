package leon.home.jagex.solution;

import leon.home.jagex.solution.Calculator2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Calculator2UTest {
    Calculator2 calculator = new Calculator2();

    @Test
    public void simpleAddition_ReturnsCorrectResult() {
        // given
        String expression = "1 + 2";
        String expected = "3";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void multipleOperationsWithoutParentheses_ReturnsCorrectResult() {
        // given
        String expression = "2 + 3 * 4";
        String expected = "14";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void multipleOperationsWithParentheses_ReturnsCorrectResult() {
        // given
        String expression = "(2 + 3) * 4";
        String expected = "20";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void nestedParentheses_ReturnsCorrectResult() {
        // given
        String expression = "((2 + 3) * 4) / 5";
        String expected = "4";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void exponentiation_ReturnsCorrectResult() {
        // given
        String expression = "2 ^ 3";
        String expected = "8";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void whitespace_ReturnsCorrectResult() {
        // given
        String expression = " 2 + 3 ";
        String expected = "5";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }
}