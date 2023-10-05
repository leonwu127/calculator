package leon.home.jagex.calculator;

import leon.home.jagex.operator.BinaryOperator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TwoOperandCalculator {
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


    public BigDecimal calculate(BinaryOperator operator, BigDecimal m, BigDecimal n, int scale) {

        BigDecimal result = BigDecimal.ZERO;
        switch (operator) {
            case ADD:
                result = m.add(n);
                break;
            case SUBTRACT:
                result = m.subtract(n);
                break;
            case MULTIPLY:
                result = m.multiply(n).setScale(scale, RoundingMode.HALF_UP);
                break;
            case DIVIDE:
                result = m.divide(n,scale, RoundingMode.HALF_UP);
                break;
            case EXPONENT:
                result = pow(m,n,scale);
                break;
        }
        return result;
    }

    private BigDecimal pow(BigDecimal m, BigDecimal n, int scale) {
        return BigDecimal.valueOf(Math.pow(m.doubleValue(), n.doubleValue())).setScale(scale, RoundingMode.HALF_UP);
    }
}
