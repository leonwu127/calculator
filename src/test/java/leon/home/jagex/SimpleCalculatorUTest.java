package leon.home.jagex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorUTest {

    // the test method name will follow the given_when_then naming convention
    @Test
    public void _twoIntegerAddition_returnPositiveIntegerInString() {
        // given
        SimpleCalculator calculator = new SimpleCalculator();

        // when
        String result = calculator.calculate("1+2");

        // then
        assertEquals("3", result);
    }

    @Test
    public void _twoIntegerSubtraction_returnNegativeIntegerInString() {
        // given
        SimpleCalculator calculator = new SimpleCalculator();

        // when
        String result = calculator.calculate("1-5");

        // then
        assertEquals("-4", result);
    }

    @Test
    public void _twoIntegerMultiplication_returnPositiveIntegerInString() {
        // given
        SimpleCalculator calculator = new SimpleCalculator();

        // when
        String result = calculator.calculate("3*4");

        // then
        assertEquals("12", result);
    }

    @Test
    public void _twoIntegerDivision_returnIntegerInString() {
        // given
        SimpleCalculator calculator = new SimpleCalculator();

        // when
        String result = calculator.calculate("8/2");

        // then
        assertEquals("4", result);
    }

    @Test
    public void _integerExponentiation_returnIntegerInString() {
        // given
        SimpleCalculator calculator = new SimpleCalculator();

        // when
        String result = calculator.calculate("2^3");

        // then
        assertEquals("8", result);
    }

    @Test
    public void _invalidOperator_throwsIllegalArgumentException() {
        // given
        SimpleCalculator calculator = new SimpleCalculator();

        // when
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculate("1&2")
        );

        // then
        assertTrue(thrown.getMessage().contains("Invalid operator in input: 1&2"));
    }
}