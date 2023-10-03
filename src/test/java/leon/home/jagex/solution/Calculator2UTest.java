package leon.home.jagex.solution;

import leon.home.jagex.solution.Calculator2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Calculator2UTest {
    @Test
    public void simpleAddition_ReturnsCorrectResult() {
        // given
        Calculator2 calculator = new Calculator2();

        // when
        String result = calculator.calculate("1 + 2");

        // then
        assertEquals("3", result);
    }

    @Test
    public void multipleOperationsWithoutParentheses_ReturnsCorrectResult() {
        // given
        Calculator2 calculator = new Calculator2();

        // when
        String result = calculator.calculate("2 + 3 * 4");

        // then
        assertEquals("14", result);
    }

    @Test
    public void multipleOperationsWithParentheses_ReturnsCorrectResult() {
        // given
        Calculator2 calculator = new Calculator2();

        // when
        String result = calculator.calculate("(2 + 3) * 4");

        // then
        assertEquals("20", result);
    }

    @Test
    public void nestedParentheses_ReturnsCorrectResult() {
        // given
        Calculator2 calculator = new Calculator2();

        // when
        String result = calculator.calculate("((2 + 3) * 4) / 5");

        // then
        assertEquals("4", result);
    }

    @Test
    public void exponentiation_ReturnsCorrectResult() {
        // given
        Calculator2 calculator = new Calculator2();

        // when
        String result = calculator.calculate("2 ^ 3");

        // then
        assertEquals("8", result);
    }

    @Test
    public void whitespace_ReturnsCorrectResult() {
        // given
        Calculator2 calculator = new Calculator2();

        // when
        String result = calculator.calculate(" 2   +   3 ");

        // then
        assertEquals("5", result);
    }
}