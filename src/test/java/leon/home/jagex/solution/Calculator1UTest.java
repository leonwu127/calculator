package leon.home.jagex.solution;

import leon.home.jagex.exceptions.InvalidExpressionException;
import leon.home.jagex.exceptions.InvalidOperatorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Calculator1UTest {
    Calculator1 calculator = new Calculator1();
    @Test
    public void simpleAddition_ReturnPositiveIntegerInString() {
        // given
        String expression = "1+2";
        String expected = "3";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void simpleSubtraction_ReturnNegativeIntegerInString() {
        // given
        String expression = "1-2";
        String expected = "-1";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void simpleMultiplication_ReturnPositiveIntegerInString() {
        // given
        String expression = "3*4";
        String expected = "12";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void simpleDivision_ReturnIntegerInString() {
        // given
        String expression = "8/2";
        String expected = "4";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void simpleExponentiation_ReturnIntegerInString() {
        // given
        String expression = "2^3";
        String expected = "8";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void invalidOperator_ThrowsInvalidExpressionException() {
        // given
        String expression = "1&2";
        String expectedMsg = "Invalid operator 1&2";

        // when
        InvalidOperatorException thrown = assertThrows(
                InvalidOperatorException.class,
                () -> calculator.calculate(expression)
        );

        // then
        assertTrue(thrown.getMessage().contains(expectedMsg));
    }

    @Test
    public void moreThanTwoOperands_ThrowsInvalidExpressionException() {
        // given
        String expression = "1+2+3";
        String expectedMsg = "Input should contain exactly two operands: 1+2+3";

        // when
        InvalidExpressionException thrown = assertThrows(
                InvalidExpressionException.class,
                () -> calculator.calculate(expression)
        );

        // then
        assertTrue(thrown.getMessage().contains(expectedMsg));
    }

    @Test
    public void negativeOperand_ThrowsInvalidExpressionException() {
        // given
        String expression = "1+-2";
        String expectedMsg = "Input should contain only positive integers: 1+-2";

        // when
        InvalidExpressionException thrown = assertThrows(
                InvalidExpressionException.class,
                () -> calculator.calculate(expression)
        );

        // then
        assertTrue(thrown.getMessage().contains(expectedMsg));
    }

    @Test
    public void zeroDivisor_ThrowsArithmeticException() {
        // given
        String expression = "1/0";
        String expectedMsg = "/ by zero";

        // when
        ArithmeticException thrown = assertThrows(
                ArithmeticException.class,
                () -> calculator.calculate(expression)
        );

        // then
        assertTrue(thrown.getMessage().contains(expectedMsg));
    }
}