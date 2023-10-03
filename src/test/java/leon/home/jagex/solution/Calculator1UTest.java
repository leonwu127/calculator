package leon.home.jagex.solution;

import leon.home.jagex.solution.Calculator1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Calculator1UTest {
    @Test
    public void simpleAddition_ReturnPositiveIntegerInString() {
        // given
        Calculator1 calculator = new Calculator1();

        // when
        String result = calculator.calculate("1+2");

        // then
        assertEquals("3", result);
    }

    @Test
    public void simpleSubtraction_ReturnNegativeIntegerInString() {
        // given
        Calculator1 calculator = new Calculator1();

        // when
        String result = calculator.calculate("1-5");

        // then
        assertEquals("-4", result);
    }

    @Test
    public void simpleMultiplication_ReturnPositiveIntegerInString() {
        // given
        Calculator1 calculator = new Calculator1();

        // when
        String result = calculator.calculate("3*4");

        // then
        assertEquals("12", result);
    }

    @Test
    public void simpleDivision_ReturnIntegerInString() {
        // given
        Calculator1 calculator = new Calculator1();

        // when
        String result = calculator.calculate("8/2");

        // then
        assertEquals("4", result);
    }

    @Test
    public void simpleExponentiation_ReturnIntegerInString() {
        // given
        Calculator1 calculator = new Calculator1();

        // when
        String result = calculator.calculate("2^3");

        // then
        assertEquals("8", result);
    }

    @Test
    public void invalidOperator_ThrowsIllegalArgumentException() {
        // given
        Calculator1 calculator = new Calculator1();

        // when
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculate("1&2")
        );

        // then
        assertTrue(thrown.getMessage().contains("Invalid operator in input: 1&2"));
    }

    @Test
    public void moreThanTwoOperands_ThrowsIllegalArgumentException() {
        // given
        Calculator1 calculator = new Calculator1();

        // when
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculate("1+2+3")
        );

        // then
        assertTrue(thrown.getMessage().contains("Input should contain exactly two operands: 1+2+3"));
    }

    @Test
    public void negativeOperand_ThrowsIllegalArgumentException() {
        // given
        Calculator1 calculator = new Calculator1();

        // when
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculate("1+-2")
        );

        // then
        assertTrue(thrown.getMessage().contains("Input should contain only positive integers: 1+-2"));
    }

    @Test
    public void zeroDivisor_ThrowsArithmeticException() {
        // given
        Calculator1 calculator = new Calculator1();

        // when
        ArithmeticException thrown = assertThrows(
                ArithmeticException.class,
                () -> calculator.calculate("1/0")
        );

        // then
        assertTrue(thrown.getMessage().contains("/ by zero"));
    }
}