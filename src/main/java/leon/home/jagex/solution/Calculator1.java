package leon.home.jagex.solution;

import leon.home.jagex.operator.BinaryOperator;
import leon.home.jagex.calculator.IntegerSimpleCalculator;
import leon.home.jagex.calculator.TwoOperandCalculator;
import static leon.home.jagex.util.ExpressionSplitter.*;

public class Calculator1 {
    
    TwoOperandCalculator simpleCalculator = new IntegerSimpleCalculator();

    public String calculate(String expression) {
        expression = expression.replace(" ", "");
        BinaryOperator operator = getOperator(expression);
        String[] operands = getPositiveIntegers(expression, operator);

        return simpleCalculator.calculate(operator, operands[0], operands[1]);
    }


}
