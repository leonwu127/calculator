package leon.home.jagex.util;

import leon.home.jagex.operator.BinaryOperator;
import leon.home.jagex.operator.UnaryOperator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExpressionHelper {

    public static final String REGEX_MATCH_HEX = "^(0[xX])?([0-9]*[a-fA-F][0-9a-fA-F]*)$";
    public static final Map<String, Integer> operatorPriorityMap;

    static {
        operatorPriorityMap = new ConcurrentHashMap<>();
        operatorPriorityMap.put(BinaryOperator.ADD.formattedSymbol(), 1);
        operatorPriorityMap.put(BinaryOperator.SUBTRACT.formattedSymbol(), 1);
        operatorPriorityMap.put(BinaryOperator.MULTIPLY.formattedSymbol(), 2);
        operatorPriorityMap.put(BinaryOperator.DIVIDE.formattedSymbol(), 2);
        operatorPriorityMap.put(UnaryOperator.NEGATE.formattedSymbol(), 3);
        operatorPriorityMap.put(BinaryOperator.EXPONENT.formattedSymbol(), 4);
    }

    public static BinaryOperator getOperator(String token) {
        for (BinaryOperator op : BinaryOperator.values()) {
            if (token.contains(op.formattedSymbol())) {
                return op;
            }
        }
        throw new IllegalArgumentException("Invalid operator " + token);
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

}
