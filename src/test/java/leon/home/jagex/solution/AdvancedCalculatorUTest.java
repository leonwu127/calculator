package leon.home.jagex.solution;

import leon.home.jagex.exceptions.InvalidFactorialException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdvancedCalculatorUTest {

    AdvancedCalculator calculator = new AdvancedCalculator(4);
    @Test
    public void complexFloatingPointsExpression_calculateAndScaleTo4DecimalPlaces_resultWith4DecimalPlaces() {
        // given
        String expression = "1.23456789 + 2.3456789 * 3.456789 / 4.56789";
        String expected = "3.0097";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void complexExpressionWithFunctions_calculateAndScaleTo4DecimalPlaces_resultWith4DecimalPlaces() {
        // given
        String expression = "sin(1.23456789) + cos(2.3456789) * tan(3.456789) / log(4.56789)";
        String expected = "0.5982";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void complexExpressionWithFactorial_calculateAndScaleTo4DecimalPlaces_resultWith4DecimalPlaces() {
        // given
        String expression = "1.23456789 + 2! * 3.456789 / 4.56789";
        String expected = "2.7481";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void factorialOfNegativeNumber_throwInvalidFactorialException() {
        // given
        String expression = "(-1)!";
        String expected = "Factorial operand should be a positive integer: -1.0";

        // when
        Exception exception = assertThrows(InvalidFactorialException.class, () -> calculator.calculate(expression, 4));

        // then
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void factorialOfDecimalNumber_throwInvalidFactorialException() {
        // given
        String expression = "1.5!";
        String expected = "Factorial operand should be a positive integer: 1.5";

        // when
        Exception exception = assertThrows(InvalidFactorialException.class, () -> calculator.calculate(expression, 4));

        // then
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void factorialOfZero_returnOne() {
        // given
        String expression = "0!";
        String expected = "1";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void factorialOfSimpleAdditionExpression_calculateAndScaleTo4DecimalPlaces_resultWith4DecimalPlaces() {
        // given
        String expression = "(1 + 2)!";
        String expected = "6";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void sinOfNegativeNumber_calculateAndScaleTo4DecimalPlaces_resultWith4DecimalPlaces() {
        // given
        String expression = "sin(-1.23456789)";
        String expected = "-0.944";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void _3sinOfNegativeNumber_calculateAndScaleTo4DecimalPlaces_resultWith4DecimalPlaces() {
        // given
        String expression = "3*sin(-1.23456789)";
        String expected = "-2.832";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void _additionSinOfNegativeNumber_calculateAndScaleTo4DecimalPlaces_resultWith4DecimalPlaces() {
        // given
        String expression = "-(1+2)*sin(-1.23456789)";
        String expected = "2.832";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals(expected, result);
    }
}