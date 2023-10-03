package leon.home.jagex.util;

import leon.home.jagex.operator.BinaryOperator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExpressionSplitter {

    public static final Map<Character, BinaryOperator> operatorPriorityMap;

    static {
        operatorPriorityMap = new ConcurrentHashMap<>();
        operatorPriorityMap.put(BinaryOperator.ADD.getSymbol(), BinaryOperator.ADD);
        operatorPriorityMap.put(BinaryOperator.SUBTRACT.getSymbol(), BinaryOperator.SUBTRACT);
        operatorPriorityMap.put(BinaryOperator.MULTIPLY.getSymbol(), BinaryOperator.MULTIPLY);
        operatorPriorityMap.put(BinaryOperator.DIVIDE.getSymbol(), BinaryOperator.DIVIDE);
        operatorPriorityMap.put(BinaryOperator.EXPONENT.getSymbol(), BinaryOperator.EXPONENT);
    }

    public static BinaryOperator getOperator(String expression) {
        BinaryOperator operator = null;
        for (BinaryOperator op : BinaryOperator.values()) {
            if (expression.indexOf(op.getSymbol()) != -1) {
                operator = op;
                break;
            }
        }
        if (operator == null) {
            throw new IllegalArgumentException("Invalid operator in input: " + expression);
        }

        return operator;
    }

    public static String[] getPositiveIntegers(String expression, BinaryOperator operator) {
        String[] operands = expression.split(String.format("\\%s", operator.getSymbol()));  // Escape the operator for regex
        if (operands.length != 2) {
            throw new IllegalArgumentException("Input should contain exactly two operands: " + expression);
        }
        for (String operand : operands) {
            if (!operand.matches("\\d+")) {
                throw new IllegalArgumentException("Input should contain only positive integers: " + expression);
            }
        }
        return operands;
    }


    public static String[] getDecimalOperands(String expression, BinaryOperator operator) {
        String[] operands = expression.split(String.format("\\%s", operator.getSymbol()));  // Escape the operator for regex
        if (operands.length != 2) {
            throw new IllegalArgumentException("Input should contain exactly two operands: " + expression);
        }
        return operands;
    }

}
