package leon.home.jagex.util;

import leon.home.jagex.function.MathFunction;
import leon.home.jagex.operator.BinaryOperator;
import leon.home.jagex.operator.UnaryOperator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExpressionHelper {


    public static final Map<String, MathFunction> supportedFunctions = getSupportedFunctions();
    public static final Map<Character, UnaryOperator> supportedUnaryOperators = getSupportedUnaryOperators();

    private static Map<Character, UnaryOperator> getSupportedUnaryOperators() {
        Map<Character, UnaryOperator> operatorMap = new HashMap<>();
        for (UnaryOperator operator : UnaryOperator.values()) {
            operatorMap.put(operator.getSymbol(), operator);
        }
        return operatorMap;
    }

    private static Map<Character, BinaryOperator> getSupportedBinaryOperators() {
        Map<Character, BinaryOperator> operatorMap = new HashMap<>();
        for (BinaryOperator operator : BinaryOperator.values()) {
            operatorMap.put(operator.getSymbol(), operator);
        }
        return operatorMap;
    }

    private static Map<String, MathFunction> getSupportedFunctions() {
        Map<String, MathFunction> functionMap = new HashMap<>();
        for (MathFunction function : MathFunction.values()) {
            functionMap.put(function.getName(), function);
        }
        return functionMap;
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
