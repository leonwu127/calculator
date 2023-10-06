package leon.home.jagex.calculator;

import leon.home.jagex.exceptions.InvalidFactorialException;
import leon.home.jagex.exceptions.UnsupportedTokenException;
import leon.home.jagex.operator.UnaryOperator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class FunctionCalculator {
    public BigDecimal calculate(String function, BigDecimal operand1,  int scale) {
        switch (function) {
            case "sin":
                return BigDecimal.valueOf(Math.sin(operand1.doubleValue()))
                        .setScale(scale, RoundingMode.HALF_UP);
            case "cos":
                return BigDecimal.valueOf(Math.cos(operand1.doubleValue()))
                        .setScale(scale, RoundingMode.HALF_UP);
            case "tan":
                return BigDecimal.valueOf(Math.tan(operand1.doubleValue()))
                        .setScale(scale, RoundingMode.HALF_UP);
            case "log":
                return BigDecimal.valueOf(Math.log10(operand1.doubleValue()))
                        .setScale(scale, RoundingMode.HALF_UP);
            default:
                throw new UnsupportedTokenException("Unsupported function: " + function);
        }
    }

    public BigDecimal calculateFactorial(String token, BigDecimal operand) {
        if (token.equals(UnaryOperator.FACTORIAL.formattedSymbol())) {
            return factorial(operand);
        }
        throw new UnsupportedTokenException("Unsupported token: " + token);
    }
    private BigDecimal factorial(BigDecimal operand) {
        if (operand.compareTo(BigDecimal.ZERO) < 0 || operand.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) != 0) {
            throw new InvalidFactorialException("Factorial operand should be a positive integer: " + operand);
        }

        if (operand.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        BigDecimal result = BigDecimal.ONE;
        for (BigDecimal i = BigDecimal.ONE; i.compareTo(operand) <= 0; i = i.add(BigDecimal.ONE)) {
            result = result.multiply(i);
        }
        return result;
    }
}
