package leon.home.jagex.solution;

import leon.home.jagex.parsers.RPNIntegerParser;
import leon.home.jagex.parsers.ReversePolishNotationParser;
import leon.home.jagex.calculator.TwoOperandCalculator;

import java.util.*;

import static leon.home.jagex.util.ExpressionHelper.getOperator;
import static leon.home.jagex.util.TokenHelper.isDecimalNumberToken;

/**
 * This Calculator support the following features:
 * 1. Support for multiple integers calculations only
 * 2. Support for parentheses
 * 3. Support for +, -, *, /, ^ operators
 */
public class Calculator2 implements Calculator{
    private final TwoOperandCalculator simpleCalculator;
    private final ReversePolishNotationParser rpn;

    public Calculator2() {
        this.simpleCalculator = new TwoOperandCalculator();
        this.rpn = new RPNIntegerParser();
    }

    @Override
    public String calculate(String expression) {
        expression = expression.replace(" ", "");
        List<String> postfix = rpn.parse(expression);
        return evaluatePostfix(postfix);
    }

    private String evaluatePostfix(List<String> postfix) {
        Deque<String> stack = new ArrayDeque<>();

        for (String token : postfix) {
            if (isDecimalNumberToken(token)) {
                stack.push(token);
            } else {
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                String result = simpleCalculator.calculateBigDecimal(getOperator(token),operand1, operand2);
                stack.push(result);
            }
        }

        return stack.pop();
    }
}
