package leon.home.jagex.solution;

import leon.home.jagex.calculator.TwoOperandCalculator;
import leon.home.jagex.operator.UnaryOperator;
import leon.home.jagex.parsers.RPNDecimalParser;
import leon.home.jagex.parsers.ReversePolishNotationParser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static leon.home.jagex.util.ExpressionHelper.getOperator;
import static leon.home.jagex.util.TokenHelper.isDecimalNumberToken;

public class Calculator3 {

    private final TwoOperandCalculator simpleCalculator;
    private final ReversePolishNotationParser rpn;

    private final int DEFAULT_SCALE = 3;

    public Calculator3() {
        this.simpleCalculator = new TwoOperandCalculator();
        this.rpn = new RPNDecimalParser();
    }

    public String calculate(String expression) {
        expression = expression.replace(" ", "");
        List<String> postfix = rpn.parse(expression);
        return evaluatePostfix(postfix);
    }

    private String evaluatePostfix(List<String> postfix) {
        Deque<BigDecimal> stack = new ArrayDeque<>();

        for (String token : postfix) {
            if (isDecimalNumberToken(token)) {
                stack.push(BigDecimal.valueOf(Double.parseDouble(token)));
            } else if (token.equals(UnaryOperator.NEGATE.formattedSymbol())) {
                stack.push(stack.pop().negate());
            } else {
                BigDecimal operand2 = stack.pop();
                BigDecimal operand1 = stack.pop();
                BigDecimal result = simpleCalculator.calculateBigDecimal(getOperator(token), operand1, operand2, DEFAULT_SCALE);
                stack.push(result);
            }
        }

        return stack.pop().setScale(DEFAULT_SCALE, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

}
