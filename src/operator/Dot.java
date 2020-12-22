package operator;

import java.io.IOException;
import java.util.List;

public class Dot implements Operator {
    private final static String SIGN = ".";

    private final static List<String> PREVIOUSLY_ALLOWS = List.of();

    public static void isAllowedByPrevious(Operator operator) throws IOException {
        if (operator.getSign() == null) {
            if (! (operator instanceof Numeric)) {
                throw new IOException("Invalid expression!");
            }
        } else {
            if (! PREVIOUSLY_ALLOWS.contains(operator.getSign())) {
                throw new IOException("Invalid expression!");
            }
        }
    }

    @Override
    public String getSign() {
        return SIGN;
    }
}
