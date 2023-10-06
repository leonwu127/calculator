package leon.home.jagex.parsers;

import leon.home.jagex.exceptions.UnrecognizedSymbolException;
import leon.home.jagex.exceptions.UnsupportedTokenException;
import leon.home.jagex.operator.BinaryOperator;
import leon.home.jagex.operator.Parenthesis;
import leon.home.jagex.operator.UnaryOperator;

import java.util.*;

import static leon.home.jagex.util.TokenHelper.*;

public class RPNHexadecimalParser implements ReversePolishNotationParser {
    private final Map<String, BinaryOperator> supportedBinaryOperators;

    public RPNHexadecimalParser(Map<String, BinaryOperator> supportedBinaryOperators) {
        this.supportedBinaryOperators = supportedBinaryOperators;
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

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || Character.isLetter(c) || c == '.') {
                currentToken.append(c);
            } else if (isUnaryNegation(c, i, expression)) {
                tokens.add(UnaryOperator.NEGATE.formattedSymbol());
            } else if (isOperatorOrParenthesis(c)) {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                tokens.add(String.valueOf(c));
            } else throw new UnrecognizedSymbolException(String.format("Unrecognized symbol in expression: %s, %s",
                    c, expression));
        }

        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
            currentToken.setLength(0);
        }

        return tokens;
    }


    private boolean isUnaryNegation(char c, int index, String expression) {
        return c == UnaryOperator.NEGATE.getSymbol() && (index == 0 ||
                expression.charAt(index - 1) == Parenthesis.LEFT_PAREN.getSymbol() ||
                precedenceMap.containsKey(String.valueOf(expression.charAt(index - 1))));
    }

    private boolean isOperatorOrParenthesis(char c) {
        return supportedBinaryOperators.containsKey(String.valueOf(c)) ||
                c == Parenthesis.LEFT_PAREN.getSymbol() ||
                c == Parenthesis.RIGHT_PAREN.getSymbol();
    }

    public boolean isNumber(String token) {
        return token.matches(REGEX_MATCH_HEX) || token.matches(REGEX_MATCH_BIG_DECIMAL);
    }
}
