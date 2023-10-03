package leon.home.jagex.calculator;

import leon.home.jagex.operator.BinaryOperator;

public class IntegerSimpleCalculator implements TwoOperandCalculator{

    @Override
    public String calculate(BinaryOperator operator, String operand1, String operand2) {
        int m = Integer.parseInt(operand1);
        int n = Integer.parseInt(operand2);

        if (m < 0 || n < 0) {
            throw new IllegalArgumentException(String.format("Both operands should be positive integers: %s,%s",
                    operand1, operand2));
        }
        int result = 0;
        switch (operator) {
            case ADD:
                result = m + n;
                break;
            case SUBTRACT:
                result = m - n;
                break;
            case MULTIPLY:
                result = m * n;
                break;
            case DIVIDE:
                result = m / n;
                break;
            case EXPONENT:
                result = (int) Math.pow(m, n);
                break;
        }
        return String.valueOf(result);
    }
}
