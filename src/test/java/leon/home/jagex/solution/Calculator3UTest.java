package leon.home.jagex.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Calculator3UTest {
    @Test
    public void decimalExpression_calculating_correctResult() {
        // given
        Calculator3 calculator = new Calculator3();
        String expression = "3.5 * 3";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals("10.5", result);
    }

    @Test
    public void negativeDecimalExpression_calculating_correctResult() {
        // given
        Calculator3 calculator = new Calculator3();
        String expression = "-53 + -24";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals("-77", result);
    }

    @Test
    public void expressionWithParentheses_calculating_correctResult() {
        // given
        Calculator3 calculator = new Calculator3();
        String expression = "10 / 3";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals("3.333", result);
    }

    @Test
    public void negativeExpressionWithParentheses_negativeResult() {
        // given
        Calculator3 calculator = new Calculator3();
        String expression = "(-20 * 1.8) / 2";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals("-18", result);
    }

    @Test  //-12.315 - 42
    public void expressionWithNegativeDecimal_negativeResult() {
        // given
        Calculator3 calculator = new Calculator3();
        String expression = "-12.315 - 42";

        // when
        String result = calculator.calculate(expression);

        // then
        assertEquals("-54.315", result);
    }

}