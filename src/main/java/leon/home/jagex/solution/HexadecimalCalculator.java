package leon.home.jagex.solution;

import leon.home.jagex.calculator.TwoOperandCalculator;
import leon.home.jagex.operator.BinaryOperator;
import leon.home.jagex.operator.UnaryOperator;
import leon.home.jagex.parsers.RPNHexadecimalParser;
import leon.home.jagex.parsers.ReversePolishNotationParser;

import java.util.*;

import static leon.home.jagex.util.ExpressionHelper.getOperator;
import static leon.home.jagex.util.TokenHelper.isDecimalNumberToken;
import static leon.home.jagex.util.TokenHelper.isHexadecimalNumberToken;

/**
 * This Calculator support the following features:
 * 1. Support for hexadecimal numbers long integers only
 * 2. Support for negative numbers
 * 3. Support for +, -, *, /, ^ operators
 * 4. Hex number should be prefixed with 0x
 */
public class HexadecimalCalculator implements Calculator{

    private final Map<String, BinaryOperator> supportedBinaryOperators;
    private final TwoOperandCalculator simpleCalculator;
    private final ReversePolishNotationParser rpn;

    public HexadecimalCalculator() {
        supportedBinaryOperators = hexCalculatorSupportedBinaryOperators();
        this.rpn = new RPNHexadecimalParser(supportedBinaryOperators);
        this.simpleCalculator = new TwoOperandCalculator();
        
    }
    
    private Map<String,BinaryOperator> hexCalculatorSupportedBinaryOperators() {
        Map<String,BinaryOperator> supportedBinaryOperators = new HashMap<>();
        supportedBinaryOperators.put(BinaryOperator.ADD.formattedSymbol(), BinaryOperator.ADD);
        supportedBinaryOperators.put(BinaryOperator.SUBTRACT.formattedSymbol(), BinaryOperator.SUBTRACT);
        supportedBinaryOperators.put(BinaryOperator.MULTIPLY.formattedSymbol(), BinaryOperator.MULTIPLY);
        supportedBinaryOperators.put(BinaryOperator.DIVIDE.formattedSymbol(), BinaryOperator.DIVIDE);
        supportedBinaryOperators.put(BinaryOperator.EXPONENT.formattedSymbol(), BinaryOperator.EXPONENT);
        return supportedBinaryOperators;
    }

    @Override
    public String calculate(String expression) {
        expression = expression.replace(" ", "");
        return evaluatePostfix(rpn.parse(expression));
    }

    private String evaluatePostfix(List<String> postfix) {
        Deque<Long> stack = new ArrayDeque<>();

        for (String token : postfix) {
            if (isDecimalNumberToken(token)) {
                stack.push(Long.parseLong(token));
            } else if (isHexadecimalNumberToken(token)) {
                stack.push(Long.parseLong(token.substring(2), 16));
            } else if (token.equals(UnaryOperator.NEGATE.formattedSymbol())) {
                stack.push(-stack.pop());
            } else if (supportedBinaryOperators.containsKey(token)) {
                Long operand2 = stack.pop();
                Long operand1 = stack.pop();
                stack.push(simpleCalculator.calculateLong(getOperator(token), operand1, operand2));
            } else {
                throw new UnsupportedOperationException(String.format("Unsupported token in expression: %s",
                        token));
            }
        }

        long result = stack.pop();
        if (result < 0) {
            return String.format("-0x%s",Long.toHexString(-result).toUpperCase());
        }
        return String.format("0x%s",Long.toHexString(result).toUpperCase());
    }


}
