package operator;

import java.io.IOException;
import java.util.List;

public class Ch implements Operator {
    private final static String SIGN = "c";

    private final static List<String> PREVIOUSLY_ALLOWS = List.of("c");

    public static void isAllowedByPrevious(Operator operator) throws IOException {
        if (! PREVIOUSLY_ALLOWS.contains(operator.getSign()) || ! (operator instanceof Numeric)) {
            throw new IOException("Invalid expression!");
        }
    }

    public static void reverse(String value, String expression, int indexValue) {
        if (expression.charAt(indexValue + 1) == 'c') {

        }
    }

    @Override
    public String getSign() {
        return SIGN;
    }
}
