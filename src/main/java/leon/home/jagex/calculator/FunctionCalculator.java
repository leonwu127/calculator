package leon.home.jagex.calculator;

import leon.home.jagex.exceptions.UnsupportedTokenException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FunctionCalculator {
    public BigDecimal calculate(String function, BigDecimal operand, int scale) {
        switch (function) {
            case "sin":
                return BigDecimal.valueOf(Math.sin(operand.doubleValue())).setScale(scale, RoundingMode.HALF_UP);
            case "cos":
                return BigDecimal.valueOf(Math.cos(operand.doubleValue())).setScale(scale, RoundingMode.HALF_UP);
            case "tan":
                return BigDecimal.valueOf(Math.tan(operand.doubleValue())).setScale(scale, RoundingMode.HALF_UP);
            case "log":
                return BigDecimal.valueOf(Math.log10(operand.doubleValue())).setScale(scale, RoundingMode.HALF_UP);
            default:
                throw new UnsupportedTokenException("Unsupported function: " + function);
        }
    }
}
