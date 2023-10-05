package leon.home.jagex.parsers;

import leon.home.jagex.exceptions.InvalidExpressionException;
import leon.home.jagex.exceptions.UnsupportedTokenException;
import leon.home.jagex.operator.Parenthesis;
import leon.home.jagex.operator.UnaryOperator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static leon.home.jagex.util.ExpressionHelper.REGEX_MATCH_HEX;
import static leon.home.jagex.util.ExpressionHelper.operatorPriorityMap;

public class RPNHexadecimalParser implements ReversePolishNotationParser {
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
            } else if (operatorPriorityMap.containsKey(token)) {
                while (!stack.isEmpty() && operatorPriorityMap.containsKey(stack.peek())
                        && operatorPriorityMap.get(stack.peek())
                        >= operatorPriorityMap.get(token)) {
                    postfix.add(stack.pop());
                }
                stack.push(token);
            } else {
                throw new UnsupportedTokenException(String.format("Unsupported token in expression: %s, %s",
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
        boolean isHex = false;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                currentToken.append(c);
            } else {
                if (Character.toLowerCase(c) >= 'a' && Character.toLowerCase(c) <= 'f'
                || Character.toLowerCase(c) == 'x' && i == 1 && expression.charAt(i - 1) == '0') {
                    currentToken.append(c);
                    isHex = true;
                    continue;
                }

                if (c == UnaryOperator.NEGATE.getSymbol() &&
                        (i == 0 || expression.charAt(i - 1) == Parenthesis.LEFT_PAREN.getSymbol() ||
                                operatorPriorityMap.containsKey(String.valueOf(expression.charAt(i - 1))))) {
                    tokens.add("u-");
                    continue;
                }

                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                    isHex = false;
                }

                if (operatorPriorityMap.containsKey(String.valueOf(c)) ||
                        c == Parenthesis.LEFT_PAREN.getSymbol() ||
                        c == Parenthesis.RIGHT_PAREN.getSymbol()) {
                    tokens.add(String.valueOf(c));
                } else {
                    throw new InvalidExpressionException(String.format("Invalid token in expression: %s, %s",
                            c, expression));
                }
            }
        }

        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }

        return tokens;
    }

    @Override
    public boolean isNumber(String token) {
        if (token.matches("[\\-]?\\d+(\\.\\d+)?")) {
            return true;
        }
        return token.matches(REGEX_MATCH_HEX);
    }
}
