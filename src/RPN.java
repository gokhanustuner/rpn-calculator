import java.io.IOException;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * BOUN SWE-510, 2020 Fall, Remedial class' Midterm I
 *
 * RPN is the class that contains methods regarding an RPN Calculator.
 *
 * @author Gökhan Üstüner
 **/
public class RPN {
    /**
     * @see List
     */
    private final static List<String> VALID_OPERATORS = List.of("*", "/", "+", "-", "%");

    /**
     * @see Stack
     */
    private final Stack<Float> stack;

    /**
     * Class constructor. Creates the stack instance and this instance is used for all stack operations.
     */
    public RPN() {
        stack = new Stack<>();
    }

    /**
     * Main method that controls the arithmetic and stack operations.
     *
     * @param expression The user input.
     * @return float
     * @throws IOException Is thrown if the input is invalid.
     * @throws EmptyStackException Is thrown if the stack is empty.
     */
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
            } else if (expression.equals("%")) {
                return remainder();
            } else {
                throw new IOException("Invalid expression!");
            }
        }
    }

    /**
     * Carries out the Remainder operation of an RPN Calculator, with required stack operations.
     *
     * @return float
     */
    private float remainder() {
        float rOperand = stack.pop();
        float lOperand = stack.pop();

        return stack.push(lOperand % rOperand);
    }

    /**
     * Carries out the Subtraction operation of an RPN Calculator, with required stack operations.
     *
     * @return float
     */
    private float subtract() {
        float rOperand = stack.pop();
        float lOperand = stack.pop();

        return stack.push(lOperand - rOperand);
    }

    /**
     * Carries out the Addition operation of an RPN Calculator, with required stack operations.
     *
     * @return float
     */
    private float add() {
        float rOperand = stack.pop();
        float lOperand = stack.pop();

        return stack.push(lOperand + rOperand);
    }

    /**
     * Carries out the Division operation of an RPN Calculator, with required stack operations.
     *
     * @return float
     */
    private float divide() {
        float rOperand = stack.pop();
        float lOperand = stack.pop();

        return stack.push(lOperand / rOperand);
    }

    /**
     * Carries out the Multiplication operation of an RPN Calculator, with required stack operations.
     *
     * @return float
     */
    private float multiply() {
        float rOperand = stack.pop();
        float lOperand = stack.pop();

        return stack.push(lOperand * rOperand);
    }

    /**
     * Carries out the CHS operation of an RPN Calculator, with required stack operations.
     *
     * @return float
     */
    private float changeSign() {
        float lastValue = stack.pop();

        return stack.push(-lastValue);
    }

    /**
     * Pushes numeric user inputs to the stack.
     *
     * @param expression Raw, String type user input.
     * @return float
     * @throws IOException Only numeric values are pushed to the stack. If the expression is not numeric,
     *                     the IOException will be thrown.
     */
    private float pushToStack(String expression) throws IOException {
        checkIsNumeric(expression);
        float value = Float.parseFloat(expression);

        return stack.push(value);
    }

    /**
     * Checks whether the user input is numeric.
     *
     * @param character The character, which was entered as an input by the user.
     * @throws IOException Since checking whether the user input is numeric is carried out after the valid operator check,
     *                     we throw an exception if the input is not numeric. There is not another option.
     */
    private static void checkIsNumeric(String character) throws IOException {
        try {
            Float.parseFloat(character);
        } catch (NumberFormatException e) {
            throw new IOException("Invalid expression!");
        }
    }

    /**
     * This method is called first in the apply() method. Checks whether the user input is a valid operator.
     * If it is, the operation's type is scanned by an if/else block.
     * Otherwise, it is examined whether the user input is a number.
     *
     * @param character The character, which was entered as an input by the user.
     * @return String
     */
    private static boolean isCharacterValidOperator(String character) {
        return VALID_OPERATORS.contains(character);
    }
}
