package leon.home.jagex.calculator;

import leon.home.jagex.operator.BinaryOperator;

import java.text.DecimalFormat;

public class DecimalSimpleCalculator implements TwoOperandCalculator{
    @Override
    public String calculate(BinaryOperator operator, String operand1, String operand2) {
        double m = Double.parseDouble(operand1);
        double n = Double.parseDouble(operand2);

        double result = 0;
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
                result = Math.pow(m, n);
                break;
        }
        return String.valueOf(formatValue(result));
    }
    private String formatValue(double value) {
        if (value == Math.floor(value)) {
            return String.format("%.0f", value);
        } else {
            DecimalFormat df = new DecimalFormat("#.###");
            return df.format(value);
        }
    }
}
