package leon.home.jagex.solution;

import leon.home.jagex.operator.BinaryOperator;
import leon.home.jagex.calculator.TwoOperandCalculator;
import static leon.home.jagex.util.ExpressionHelper.*;

/**
 * This Calculator support the following features:
 * 1. Support for 2 positive integers only
 * 2. Support for +, -, *, /, ^ operators
 */
public class Calculator1 implements Calculator{
    
    TwoOperandCalculator simpleCalculator = new TwoOperandCalculator();

    @Override
    public String calculate(String expression) {
        expression = expression.replace(" ", "");

        BinaryOperator operator = getOperator(expression);
        String[] operands = getPositiveIntegers(expression, operator);

        return simpleCalculator.calculateBigDecimal(operator, operands[0], operands[1]);
    }


}
