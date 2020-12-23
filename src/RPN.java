import java.io.IOException;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class RPN {
    private final static List<String> VALID_OPERATORS = List.of("*", "/", "+", "-", "%");

    private final Stack<Float> stack;

    public RPN() {
        stack = new Stack<>();
    }

    public float apply(String expression) throws IOException, EmptyStackException {
        if (! isCharacterValidOperator(expression)) {
            if (expression.equals("c")) {
                return changeSign();
            } else {
                return pushToStack(expression);
            }
        } else {
            if (expression.equals("*")) {
                return multiply();
            } else if (expression.equals("/")) {
                return divide();
            } else if (expression.equals("+")) {
                return add();
            } else if (expression.equals("-")) {
                return subtract();
            } else {
                throw new IOException("Invalid expression!");
            }
        }
    }

    private float subtract() {
        float rOperand = stack.pop();
        float lOperand = stack.pop();

        return stack.push(lOperand - rOperand);
    }

    private float add() {
        float rOperand = stack.pop();
        float lOperand = stack.pop();

        return stack.push(lOperand + rOperand);
    }

    private float divide() {
        float rOperand = stack.pop();
        float lOperand = stack.pop();

        return stack.push(lOperand / rOperand);
    }

    private float multiply() {
        float rOperand = stack.pop();
        float lOperand = stack.pop();

        return stack.push(lOperand * rOperand);
    }

    private float pushToStack(String expression) throws IOException {
        checkIsNumeric(expression);
        float value = Float.parseFloat(expression);

        return stack.push(value);
    }

    private float changeSign() {
        float lastValue = stack.pop();

        return stack.push(-lastValue);
    }

    private static void checkIsNumeric(String character) throws IOException {
        try {
            Float.parseFloat(character);
        } catch (NumberFormatException e) {
            throw new IOException("Invalid expression!");
        }
    }

    private static boolean isCharacterValidOperator(String character) {
        return VALID_OPERATORS.contains(character);
    }
}
