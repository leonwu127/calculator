package leon.home.jagex.parsers;

import leon.home.jagex.exceptions.InvalidExpressionException;
import leon.home.jagex.exceptions.UnrecognizedSymbolException;
import leon.home.jagex.function.MathFunction;
import leon.home.jagex.operator.BinaryOperator;
import leon.home.jagex.operator.Parenthesis;
import leon.home.jagex.operator.UnaryOperator;

import java.math.BigDecimal;
import java.util.*;

import static leon.home.jagex.util.TokenHelper.REGEX_MATCH_BIG_DECIMAL;
import static leon.home.jagex.util.TokenHelper.precedenceMap;

public class RPNAdvancedParser implements ReversePolishNotationParser{

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
        StringBuilder decimalToken = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c) || c == '.' || Character.isLetter(c)) {
                decimalToken.append(c);
            } else if (isUnaryNegation(c, i, expression)) {
                tokens.add(UnaryOperator.NEGATE.formattedSymbol());
            } else if (c == UnaryOperator.FACTORIAL.getSymbol()) {
                pushDecimalToken(decimalToken, tokens);
                tokens.add(UnaryOperator.FACTORIAL.formattedSymbol());
            } else if (isOperatorOrParenthesis(c)) {
                pushDecimalToken(decimalToken, tokens);
                tokens.add(String.valueOf(c));
            } else throw new UnrecognizedSymbolException(String.format("Unrecognized symbol in expression: %s, %s",
                    c, expression));
        }
        pushDecimalToken(decimalToken, tokens);
        return tokens;
    }

    private boolean isUnaryNegation(char c, int index, String expression) {
        return c == UnaryOperator.NEGATE.getSymbol() && (index == 0 ||
                expression.charAt(index - 1) == Parenthesis.LEFT_PAREN.getSymbol() ||
                precedenceMap.containsKey(String.valueOf(expression.charAt(index - 1))));
    }

    private boolean isOperatorOrParenthesis(char c) {
        return supportedBinaryOperators.containsKey(String.valueOf(c)) ||
                c == UnaryOperator.FACTORIAL.getSymbol() ||
                c == Parenthesis.LEFT_PAREN.getSymbol() ||
                c == Parenthesis.RIGHT_PAREN.getSymbol();
    }

    private void pushDecimalToken(StringBuilder decimalToken, List<String> tokens) {
        if (decimalToken.length() > 0) {
            tokens.add(decimalToken.toString());
            decimalToken.setLength(0);
        }
    }

    public boolean isNumber(String token) {
        return token.matches(REGEX_MATCH_BIG_DECIMAL);
    }
}
