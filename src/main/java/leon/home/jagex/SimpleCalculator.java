package leon.home.jagex;

public class SimpleCalculator {

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

        String[] operands = input.split("\\" + operator.getSymbol());
        int operand1 = Integer.parseInt(operands[0]);
        int operand2 = Integer.parseInt(operands[1]);

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
