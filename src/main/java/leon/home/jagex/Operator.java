package leon.home.jagex;

public enum Operator {
    ADD('+', 1),
    SUBTRACT('-', 1),
    MULTIPLY('*', 2),
    DIVIDE('/', 2),
    EXPONENT('^', 3);

    private final char symbol;
    private final int priority;

    Operator(char symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getPriority() {
        return priority;
    }
}
