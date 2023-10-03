package leon.home.jagex.solution;

import leon.home.jagex.algorithm.RPNForDecimals;
import leon.home.jagex.algorithm.ReversePolishNotationAlgorithm;
import leon.home.jagex.calculator.DecimalSimpleCalculator;
import leon.home.jagex.calculator.TwoOperandCalculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static leon.home.jagex.util.ExpressionSplitter.operatorPriorityMap;

public class Calculator3 {

    private final TwoOperandCalculator simpleCalculator;
    private final ReversePolishNotationAlgorithm rpn;

    public Calculator3() {
        this.simpleCalculator = new DecimalSimpleCalculator();
        this.rpn = new RPNForDecimals();
    }

    public String calculate(String expression) {
        expression = expression.replace(" ", "");
        expression = preprocessNegativeNumbers(expression);
        List<String> postfix = rpn.infixToPostfix(expression);
        return evaluatePostfix(postfix);
    }

    private String preprocessNegativeNumbers(String expression) {
        String regex = "-\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            String negativeNumber = matcher.group();
            expression = expression.replace(negativeNumber, "0" + negativeNumber);
        }
        return expression;
    }

    private String evaluatePostfix(List<String> postfix) {
        Deque<String> stack = new ArrayDeque<>();

        for (String str : postfix) {
            if (Character.isDigit(str.charAt(0))) {
                stack.push(str);
            } else {
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                String result = simpleCalculator.calculate(operatorPriorityMap.get(str.charAt(0)), operand1, operand2);
                stack.push(result);
            }
        }

        return stack.pop();
    }

}
