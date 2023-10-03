package leon.home.jagex.solution;

import leon.home.jagex.algorithm.CalculatorAlgorithm;
import leon.home.jagex.model.Operator;

public class Calculator1 implements CalculatorAlgorithm {

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

        int operand1 = Integer.parseInt(operands[0]);
        int operand2 = Integer.parseInt(operands[1]);

        if (operand1 < 0 || operand2 < 0) {
            throw new IllegalArgumentException("Both operands should be positive integers: " + input);
        }

        int result = 0;
        switch (operator) {
            case ADD:
                result = operand1 + operand2;
                break;
            case SUBTRACT:
                result = operand1 - operand2;
                break;
            case MULTIPLY:
                result = operand1 * operand2;
                break;
            case DIVIDE:
                result = operand1 / operand2;
                break;
            case EXPONENT:
                result = (int) Math.pow(operand1, operand2);
                break;
        }

        return String.valueOf(result);
    }
}
