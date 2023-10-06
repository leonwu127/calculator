package leon.home.jagex.parsers;

import leon.home.jagex.exceptions.InvalidExpressionException;
import leon.home.jagex.exceptions.UnrecognizedSymbolException;
import leon.home.jagex.operator.BinaryOperator;
import leon.home.jagex.operator.Parenthesis;

import java.util.*;

import static leon.home.jagex.util.TokenHelper.precedenceMap;

public class RPNIntegerParser implements ReversePolishNotationParser {

    private final Map<String, BinaryOperator> supportedBinaryOperators = supportedBinaryOperators();

    private Map<String,BinaryOperator> supportedBinaryOperators() {
        Map<String,BinaryOperator> supportedBinaryOperators = new HashMap<>();
        supportedBinaryOperators.put(BinaryOperator.ADD.formattedSymbol(), BinaryOperator.ADD);
        supportedBinaryOperators.put(BinaryOperator.SUBTRACT.formattedSymbol(), BinaryOperator.SUBTRACT);
        supportedBinaryOperators.put(BinaryOperator.MULTIPLY.formattedSymbol(), BinaryOperator.MULTIPLY);
        supportedBinaryOperators.put(BinaryOperator.DIVIDE.formattedSymbol(), BinaryOperator.DIVIDE);
        supportedBinaryOperators.put(BinaryOperator.EXPONENT.formattedSymbol(), BinaryOperator.EXPONENT);
        return supportedBinaryOperators;
    }

    @Override
    public List<String> parse(String expression) {
        List<String> postfix = new ArrayList<>();   // create a list to store postfix expression
        Deque<String> stack = new ArrayDeque<>();    // create a stack to store operators

        for (String token: tokenize(expression)) {
            if (isNumber(token)) {
                postfix.add(token);
            } else if (token.equals(Parenthesis.LEFT_PAREN.formatSymbol())) {
                stack.push(token);
            } else if (token.equals(Parenthesis.RIGHT_PAREN.formatSymbol())) {
                while (!stack.isEmpty() && !stack.peek().equals(Parenthesis.LEFT_PAREN.formatSymbol())) {
                    postfix.add(stack.pop());
                }
                stack.pop();
            } else if (precedenceMap.containsKey(token)) {
                while (!stack.isEmpty() && precedenceMap.containsKey(stack.peek())
                        && precedenceMap.get(stack.peek())
                        >= precedenceMap.get(token)) {
                    postfix.add(stack.pop());
                }
                stack.push(token);
            } else {
                throw new InvalidExpressionException(String.format("Invalid token in expression: %s, %s",
                        token, expression));
            }
        }

        while (!stack.isEmpty()) {
            postfix.add(stack.pop());
        }

        return postfix;
    }

    @Override
    public List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                currentToken.append(c);
            } else if (isOperatorOrParenthesis(c)) {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken = new StringBuilder();
                }
                tokens.add(String.valueOf(c));
            } else throw new UnrecognizedSymbolException(String.format("Unrecognized symbol in expression: %s, %s",
                    c, expression));
        }

        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }

        return tokens;
    }

    private boolean isOperatorOrParenthesis(char c) {
        return supportedBinaryOperators.containsKey(String.valueOf(c)) ||
                c == Parenthesis.LEFT_PAREN.getSymbol() ||
                c == Parenthesis.RIGHT_PAREN.getSymbol();
    }

    public boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
