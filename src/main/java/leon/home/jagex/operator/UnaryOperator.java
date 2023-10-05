package leon.home.jagex.operator;

public enum UnaryOperator {
    NEGATE('-');

    private final char symbol;

    UnaryOperator(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public String formattedSymbol() {
        return String.format("u%c",symbol);
    }
}
