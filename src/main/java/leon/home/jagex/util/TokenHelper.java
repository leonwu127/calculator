package leon.home.jagex.util;

import leon.home.jagex.function.MathFunction;
import leon.home.jagex.operator.BinaryOperator;
import leon.home.jagex.operator.UnaryOperator;

import java.util.HashMap;
import java.util.Map;

public class TokenHelper {

    public static final String REGEX_MATCH_HEX = "^(0[xX])([0-9a-fA-F]*)$";
    public static final String REGEX_MATCH_BIG_DECIMAL = "^[\\-]?\\d+(\\.\\d+)?$";
    public static boolean isDecimalNumberToken(String token) {
        return token.matches(REGEX_MATCH_BIG_DECIMAL);
    }
    public static boolean isHexadecimalNumberToken(String token) {
        return token.matches(REGEX_MATCH_HEX);
    }

    public static final Map<String, Integer> precedenceMap = getSupportedPrecedence();

    private static Map<String, Integer> getSupportedPrecedence() {
        Map<String, Integer> precedenceMap = new HashMap<>();
        precedenceMap.put(BinaryOperator.ADD.formattedSymbol(), 1);
        precedenceMap.put(BinaryOperator.SUBTRACT.formattedSymbol(), 1);
        precedenceMap.put(BinaryOperator.MULTIPLY.formattedSymbol(), 2);
        precedenceMap.put(BinaryOperator.DIVIDE.formattedSymbol(), 2);
        precedenceMap.put(UnaryOperator.NEGATE.formattedSymbol(), 3);
        precedenceMap.put(BinaryOperator.EXPONENT.formattedSymbol(), 4);
        precedenceMap.put(MathFunction.SIN.getName(), 5);
        precedenceMap.put(MathFunction.COS.getName(), 5);
        precedenceMap.put(MathFunction.TAN.getName(), 5);
        precedenceMap.put(MathFunction.LOG.getName(), 5);
        return precedenceMap;
    }

}
