package leon.home.jagex.solution;

import leon.home.jagex.calculator.TwoOperandCalculator;
import leon.home.jagex.operator.UnaryOperator;
import leon.home.jagex.parsers.RPNHexadecimalParser;
import leon.home.jagex.parsers.ReversePolishNotationParser;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static leon.home.jagex.util.ExpressionHelper.getOperator;
import static leon.home.jagex.util.ExpressionHelper.REGEX_MATCH_HEX;

public class Calculator4 {

    private final TwoOperandCalculator simpleCalculator;
    private final ReversePolishNotationParser rpn;
    private int decimalPlaces = 3;
    // Regex to match hex numbers, including 0x/0X prefix, or at least one hex digit that is not 0-9

    public Calculator4() {
        this.simpleCalculator = new TwoOperandCalculator();
        this.rpn = new RPNHexadecimalParser();
    }

    public String calculate(String expression) {
        expression = expression.replace(" ", "");
        return evaluatePostfix(rpn.parse(expression));
    }

    public String calculate(String expression, int decimalPlaces) {
        expression = expression.replace(" ", "");
        this.decimalPlaces = decimalPlaces;
        return evaluatePostfix(rpn.parse(expression));
    }

    private String evaluatePostfix(List<String> postfix) {
        Deque<BigDecimal> stack = new ArrayDeque<>();
        boolean hasHex = false;

        for (String token : postfix) {
            if (rpn.isNumber(token)) {
                if (token.matches(REGEX_MATCH_HEX)) {
                    hasHex = true;
                    String subToken = token.startsWith("0x") || token.startsWith("0X") ? token.substring(2) :
                            token;
                    stack.push(BigDecimal.valueOf(Long.parseLong(subToken, 16)));
                } else {
                    stack.push(BigDecimal.valueOf(Double.parseDouble(token)));
                }
            } else if (token.equals(UnaryOperator.NEGATE.formattedSymbol())) {
                stack.push(BigDecimal.valueOf(Double.parseDouble(token)).negate());
            } else {
                BigDecimal operand2 = stack.pop();
                BigDecimal operand1 = stack.pop();
                BigDecimal result = simpleCalculator.calculate(getOperator(token), operand1, operand2,decimalPlaces);
                stack.push(result);
            }
        }
        if (hasHex) {
            return bigDecimalToHexString(stack.pop().stripTrailingZeros(), decimalPlaces);
        }

        return stack.pop().stripTrailingZeros().toPlainString();
    }

    public static String bigDecimalToHexString(BigDecimal bd, int precision) {
        BigInteger integerPart = bd.toBigInteger();
        BigDecimal fractionalPart = bd.subtract(new BigDecimal(integerPart));

        StringBuilder hexBuilder = new StringBuilder(integerPart.toString(16));
        if (fractionalPart.compareTo(BigDecimal.ZERO) > 0) {
hexBuilder.append('.');
            for (int i = 0; i < precision && fractionalPart.compareTo(BigDecimal.ZERO) > 0; i++) {
                fractionalPart = fractionalPart.multiply(new BigDecimal(16));
                BigInteger fractionDigit = fractionalPart.toBigInteger();
                hexBuilder.append(fractionDigit.toString(16));
                fractionalPart = fractionalPart.subtract(new BigDecimal(fractionDigit));
            }
        }

        return hexBuilder.toString().toUpperCase();
    }

}
