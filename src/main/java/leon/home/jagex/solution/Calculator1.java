package leon.home.jagex.solution;

import leon.home.jagex.model.Operator;

public class Calculator1 {

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

        String[] operands = input.split(String.valueOf(operator.getSymbol()));  // Escape the operator for regex
        if (operands.length != 2) {
            throw new IllegalArgumentException("Input should contain exactly two operands: " + input);
        }

        return calculate(operator, operands[0], operands[1]);
    }

    public String calculate(Operator operator, String operand1, String operand2) {
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
