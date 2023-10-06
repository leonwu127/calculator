package leon.home.jagex.calculator;

import leon.home.jagex.exceptions.InvalidExpressionException;
import leon.home.jagex.operator.BinaryOperator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TwoOperandCalculator {
    public String calculateBigDecimal(BinaryOperator operator, String operand1, String operand2) {
        long m = Long.parseLong(operand1);
        long n = Long.parseLong(operand2);

        if (m < 0 || n < 0) {
            throw new InvalidExpressionException(String.format("Both operands should be positive integers: %s,%s",
                    operand1, operand2));
        }
        long result = 0L;
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

    public long calculateLong(BinaryOperator operator, long m, long n) {
        long result = 0L;
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
        return result;
    }

    public BigDecimal calculateBigDecimal(BinaryOperator operator, BigDecimal m, BigDecimal n, int scale) {

        BigDecimal result = BigDecimal.ZERO;
        switch (operator) {
            case ADD:
                result = m.add(n);
                break;
            case SUBTRACT:
                result = m.subtract(n);
                break;
            case MULTIPLY:
                result = m.multiply(n);
                break;
            case DIVIDE:
                result = m.divide(n, scale, RoundingMode.HALF_UP);
                break;
            case EXPONENT:
                result = BigDecimal.valueOf(Math.pow(m.doubleValue(), n.doubleValue()));
                break;
        }
        return result;
    }
}
