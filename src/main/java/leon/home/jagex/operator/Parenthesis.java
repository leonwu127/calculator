package leon.home.jagex.operator;

import java.util.Optional;

public enum Parenthesis {
    LEFT_PAREN('('),
    RIGHT_PAREN(')');

    private final char symbol;

    Parenthesis(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
