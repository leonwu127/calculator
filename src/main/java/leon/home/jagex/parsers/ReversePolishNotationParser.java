package leon.home.jagex.parsers;

import java.util.List;

public interface ReversePolishNotationParser {
    List<String> parse(String expression);
    List<String> tokenize(String expression);
}
