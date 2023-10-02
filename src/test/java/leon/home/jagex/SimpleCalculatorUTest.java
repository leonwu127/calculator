package leon.home.jagex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorUTest {

    // the test method name will follow the given_when_then naming convention
    @Test
    public void _twoIntegerAddition_returnPositiveIntegerInString() {
        // given
        SimpleCalculator calculator = new SimpleCalculator();

        // when
        String result = calculator.calculate("1+2");

        // then
        assertEquals("3", result);
    }
}