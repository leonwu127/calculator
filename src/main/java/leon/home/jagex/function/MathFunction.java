package leon.home.jagex.function;

public enum MathFunction {
    SIN("sin"),
    COS("cos"),
    TAN("tan"),
    LOG("log");

    private final String name;


    MathFunction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static boolean isFunction(String token) {
        for (MathFunction function : MathFunction.values()) {
            if (function.getName().equals(token)) {
                return true;
            }
        }
        return false;
    }

}
