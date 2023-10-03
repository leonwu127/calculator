package leon.home.jagex.solution;

import leon.home.jagex.algorithm.RPNForIntegers;
import leon.home.jagex.algorithm.ReversePolishNotationAlgorithm;
import leon.home.jagex.calculator.DecimalSimpleCalculator;
import leon.home.jagex.calculator.TwoOperandCalculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static leon.home.jagex.util.ExpressionSplitter.operatorPriorityMap;


public class Calculator2 {

    private final TwoOperandCalculator simpleCalculator;
    private final ReversePolishNotationAlgorithm rpn;

    public Calculator2() {
        this.simpleCalculator = new DecimalSimpleCalculator();
        this.rpn = new RPNForIntegers();
    }

    public String calculate(String expression) {
        expression = expression.replace(" ", "");
        List<String> postfix = rpn.infixToPostfix(expression);
        return evaluatePostfix(postfix);
    }

    private String evaluatePostfix(List<String> postfix) {
        Deque<String> stack = new ArrayDeque<>();

        for (String str : postfix) {
            if (Character.isDigit(str.charAt(0))) {
                stack.push(str);
            } else {
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                String result = simpleCalculator.calculate(operatorPriorityMap.get(str.charAt(0)),operand1, operand2);
                stack.push(result);
            }
        }

        return stack.pop();
    }
}
