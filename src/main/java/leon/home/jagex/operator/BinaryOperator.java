package leon.home.jagex.operator;

public enum BinaryOperator {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/'),
    EXPONENT('^');

    private final char symbol;

    BinaryOperator(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public String formattedSymbol() {
        return String.valueOf(symbol);
    }

    public static BinaryOperator getOperator(String token) {
        for (BinaryOperator op : BinaryOperator.values()) {
            if (token.contains(op.formattedSymbol())) {
                return op;
            }
        }
        throw new IllegalArgumentException("Invalid operator: " + token);
    }
}
