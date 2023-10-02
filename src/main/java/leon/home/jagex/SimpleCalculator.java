package leon.home.jagex;

public class SimpleCalculator {

    public String calculate(String input) {
        input = input.replace(" ", "");

        char operator = ' ';
        for (char ch : new char[] {'+', '-', '*', '/', '^'}) {
            if (input.indexOf(ch) != -1) {
                operator = ch;
                break;
            }
        }

        String[] operands = input.split("\\" + operator);  // Escape the operator for regex
        int operand1 = Integer.parseInt(operands[0]);
        int operand2 = Integer.parseInt(operands[1]);

        int result = 0;
        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                result = operand1 / operand2;
                break;
            case '^':
                result = (int) Math.pow(operand1, operand2);
                break;
        }

        return String.valueOf(result);
    }
}
