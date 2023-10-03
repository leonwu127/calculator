package leon.home.jagex.solution;

import leon.home.jagex.algorithm.CalculatorAlgorithm;
import leon.home.jagex.model.Operator;
import leon.home.jagex.model.Parenthesis;

import java.util.*;

public class Calculator2 implements CalculatorAlgorithm {

    private final Calculator1 simpleCalculator;
    private final Map<Character, Integer> operatorPriorityMap;


    public Calculator2() {
        this.simpleCalculator = new Calculator1();
        this.operatorPriorityMap = new HashMap<>();
        operatorPriorityMap.put(Operator.ADD.getSymbol(), 1);
        operatorPriorityMap.put(Operator.SUBTRACT.getSymbol(), 1);
        operatorPriorityMap.put(Operator.MULTIPLY.getSymbol(), 2);
        operatorPriorityMap.put(Operator.DIVIDE.getSymbol(), 2);
        operatorPriorityMap.put(Operator.EXPONENT.getSymbol(), 3);
    }

    @Override
    public String calculate(String expression) {
        expression = expression.replace(" ", "");
        List<String> postfix = infixToPostfix(expression);
        return evaluatePostfix(postfix);
    }

    private List<String> infixToPostfix(String input) {
        List<String> postfix = new ArrayList<>();   // create a list to store postfix expression
        Deque<Character> stack = new ArrayDeque<>();    // create a stack to store operators

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isDigit(ch)) {
                // if the character is a digit, continuously read the next character until it is not a digit
                int start = i;
                while (i + 1 < input.length() && Character.isDigit(input.charAt(i + 1))) {
                    i++;
                }
                postfix.add(input.substring(start, i + 1));
            } else if (ch == Parenthesis.LEFT_PAREN.getSymbol()) {
                // if the character is a left parenthesis, push it to the stack
                stack.push(ch);
            } else if (ch == Parenthesis.RIGHT_PAREN.getSymbol()) {
                // if the character is a right parenthesis, pop the stack until a left parenthesis is encountered
                while (!stack.isEmpty() && stack.peek() != Parenthesis.LEFT_PAREN.getSymbol()) {
                    postfix.add(String.valueOf(stack.pop()));
                }
                stack.pop();  // Pop the left parenthesis
            } else if (operatorPriorityMap.containsKey(ch)) {
                // if the character is an operator, pop the stack until an operator with lower priority is encountered
                while (!stack.isEmpty() && operatorPriorityMap.containsKey(stack.peek())
                        && operatorPriorityMap.get(stack.peek()) >= operatorPriorityMap.get(ch)) {
                    postfix.add(String.valueOf(stack.pop()));
                }
                stack.push(ch);
            } else {
                throw new IllegalArgumentException("Invalid character in input: " + ch);
            }
        }

        while (!stack.isEmpty()) {
            postfix.add(String.valueOf(stack.pop()));
        }

        return postfix;
    }

    private String evaluatePostfix(List<String> postfix) {
        Deque<String> stack = new ArrayDeque<>();

        for (String str : postfix) {
            if (Character.isDigit(str.charAt(0))) {
                stack.push(str);
            } else {
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                String result = simpleCalculator.calculate(operand1 + str + operand2);
                stack.push(result);
            }
        }

        return stack.pop();
    }
}
