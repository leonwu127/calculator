package leon.home.jagex.solution;

import leon.home.jagex.parsers.RPNIntegerParser;
import leon.home.jagex.parsers.ReversePolishNotationParser;
import leon.home.jagex.calculator.TwoOperandCalculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static leon.home.jagex.util.ExpressionHelper.getOperator;


public class Calculator2 {

    private final TwoOperandCalculator simpleCalculator;
    private final ReversePolishNotationParser rpn;

    public Calculator2() {
        this.simpleCalculator = new TwoOperandCalculator();
        this.rpn = new RPNIntegerParser();
    }

    public String calculate(String expression) {
        expression = expression.replace(" ", "");
        List<String> postfix = rpn.parse(expression);
        return evaluatePostfix(postfix);
    }

    private String evaluatePostfix(List<String> postfix) {
        Deque<String> stack = new ArrayDeque<>();

        for (String token : postfix) {
            if (rpn.isNumber(token)) {
                stack.push(token);
            } else {
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                String result = simpleCalculator.calculate(getOperator(token),operand1, operand2);
                stack.push(result);
            }
        }

        return stack.pop();
    }
}
