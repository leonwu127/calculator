package leon.home.jagex.solution;

import leon.home.jagex.model.Operator;

import java.text.DecimalFormat;

public class DecimalCalculator1 extends Calculator1{

    @Override
    public String calculate(String input) {
        input = input.replace(" ", "");

        Operator operator = null;
        for (Operator op : Operator.values()) {
            if (input.indexOf(op.getSymbol()) != -1) {
                operator = op;
                break;
            }
        }

        if (operator == null) {
            throw new IllegalArgumentException("Invalid operator in input: " + input);
        }

        String[] operands = input.split("\\" + operator.getSymbol());  // Escape the operator for regex
        if (operands.length != 2) {
            throw new IllegalArgumentException("Input should contain exactly two operands: " + input);
        }

        return calculate(operator, operands[0], operands[1]);
    }

    @Override
    public String calculate(Operator operator, String operand1, String operand2) {
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
