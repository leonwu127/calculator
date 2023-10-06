package leon.home.jagex.solution;

import leon.home.jagex.calculator.FunctionCalculator;
import leon.home.jagex.calculator.TwoOperandCalculator;
import leon.home.jagex.function.MathFunction;
import leon.home.jagex.operator.UnaryOperator;
import leon.home.jagex.parsers.RPNAdvancedParser;
import leon.home.jagex.parsers.ReversePolishNotationParser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static leon.home.jagex.util.ExpressionHelper.getOperator;
import static leon.home.jagex.util.TokenHelper.isDecimalNumberToken;

/**
 * This Calculator support the following features:
 * 1. Support for decimal numbers scaled to n decimal places
 * 2. Support for sin(), cos(), tan(), log() functions
 * 3. Support for factorial (!) operator, which considered as a function
 */
public class AdvancedCalculator implements Calculator{
    private final TwoOperandCalculator simpleCalculator;
    private final FunctionCalculator functionCalculator;
    private final ReversePolishNotationParser rpn;

    private int scale = 3;

    public AdvancedCalculator(int scale) {
        this.simpleCalculator = new TwoOperandCalculator();
        this.functionCalculator = new FunctionCalculator();
        this.rpn = new RPNAdvancedParser();
        this.scale = scale;
    }

    @Override
    public String calculate(String expression) {
        expression = expression.replace(" ", "");
        List<String> postfix = rpn.parse(expression);
        return evaluatePostfix(postfix, scale);
    }

    private String evaluatePostfix(List<String> postfix, int scale) {
        Deque<BigDecimal> stack = new ArrayDeque<>();

        for (String token : postfix) {
            if (isDecimalNumberToken(token)) {
                stack.push(BigDecimal.valueOf(Double.parseDouble(token)));
            } else if (MathFunction.isFunction(token)) {
                BigDecimal operand1 = stack.pop();
                BigDecimal result = functionCalculator.calculate(token, operand1, scale);
                stack.push(result);
            } else if (token.equals(UnaryOperator.NEGATE.formattedSymbol())) {
                stack.push(stack.pop().negate());
            } else if (token.equals(UnaryOperator.FACTORIAL.formattedSymbol())) {
                BigDecimal operand = stack.pop();
                BigDecimal result = functionCalculator.calculateFactorial(token, operand);
                stack.push(result);
            } else {
                BigDecimal operand2 = stack.pop();
                BigDecimal operand1 = stack.pop();
                BigDecimal result = simpleCalculator.calculateBigDecimal(getOperator(token), operand1, operand2, scale);
                stack.push(result);
            }
        }

        return stack.pop().setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

}
