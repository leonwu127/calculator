package leon.home.jagex.calculator;

import leon.home.jagex.operator.BinaryOperator;

public interface TwoOperandCalculator {
    String calculate(BinaryOperator operator, String operand1, String operand2);
}
