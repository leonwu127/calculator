package leon.home.jagex.util;

import java.text.DecimalFormat;

public class Formatter {
    public static String decimalFormat(double value, int precision) {
        StringBuilder sb = new StringBuilder("#.");
        for (int i = 0; i < precision; i++) {
            sb.append("#");
        }
        DecimalFormat df = new DecimalFormat(sb.toString());
        return df.format(value);
    }
}
