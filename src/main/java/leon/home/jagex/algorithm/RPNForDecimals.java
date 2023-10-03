package leon.home.jagex.algorithm;

import leon.home.jagex.operator.Parenthesis;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static leon.home.jagex.util.ExpressionSplitter.operatorPriorityMap;

public class RPNForDecimals implements ReversePolishNotationAlgorithm{
    @Override
    public List<String> infixToPostfix(String expression) {
        List<String> postfix = new ArrayList<>();   // create a list to store postfix expression
        Deque<Character> stack = new ArrayDeque<>();    // create a stack to store operators

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                // if the character is a digit, continuously read the next character until it is not a digit
                int start = i;
                while (i + 1 < expression.length() &&
                        (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '.')) {
                    i++;
                }
                postfix.add(expression.substring(start, i + 1));
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
                        && operatorPriorityMap.get(stack.peek()).getPriority()
                        >= operatorPriorityMap.get(ch).getPriority()) {
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

}
