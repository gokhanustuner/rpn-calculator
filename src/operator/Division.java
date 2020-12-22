package operator;

import java.io.IOException;
import java.util.List;

public class Division implements Operator {
    private final static String SIGN = "/";

    private final static List<String> PREVIOUSLY_ALLOWS = List.of("+", "-", "/", "*", "c");

    public static void isAllowedByPrevious(Operator operator) throws IOException {
        if (! PREVIOUSLY_ALLOWS.contains(operator.getSign()) || ! (operator instanceof Numeric)) {
            throw new IOException("Invalid expression!");
        }
    }

    @Override
    public String getSign() {
        return SIGN;
    }
}
