import operator.*;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class RPN {
    private final static List<String> VALID_OPERATORS = List.of("^", "*", "/", "+", "c", "-", ".", ",");

    private final static int MIN_EXPRESSION_LENGTH = 4;

    private static Operator previous;

    private static String[] letters;

    public static float apply(String expression) throws IOException {
        int lastSubStringStartIndex = 0;
        float rightOperand;
        float leftOperand;
        Stack<Float> stack = new Stack<>();
        letters = splitExpression(expression);
        checkLengthValidity(letters);

        for (int x = 0; x < letters.length; x++) {
            String character = getLetterByIndex(x);
            System.out.println(character);
            if (isFirstCharacter(x)) {
                checkIsNumeric(character);
                previous = new Numeric();
            } else {
                if (! isCharacterValidOperator(character)) {
                    checkIsNumeric(character);
                    previous = new Numeric();
                } else {
                    if (character.equals("^")) {
                        Caret.isAllowedByPrevious(previous);
                        previous = new Caret();
                        stack.push(Float.parseFloat(
                            expression.substring(lastSubStringStartIndex, x).replace(",", "")
                        ));
                        stack.push(Float.parseFloat(
                                expression.substring(lastSubStringStartIndex, x).replace(",", "")
                        ));
                    } else if (character.equals("*")) {
                        Multiplication.isAllowedByPrevious(previous);
                        previous = new Multiplication();
                        rightOperand = stack.pop();
                        leftOperand = stack.pop();

                        stack.push(leftOperand * rightOperand);
                        lastSubStringStartIndex = x;

                    } else if (character.equals("/")) {
                        Division.isAllowedByPrevious(previous);
                        previous = new Division();
                        rightOperand = stack.pop();
                        leftOperand = stack.pop();

                        stack.push(leftOperand / rightOperand);
                        lastSubStringStartIndex = x;
                    } else if (character.equals("+")) {
                        Summation.isAllowedByPrevious(previous);
                        previous = new Summation();
                        rightOperand = stack.pop();
                        leftOperand = stack.pop();

                        stack.push(leftOperand + rightOperand);
                        lastSubStringStartIndex = x;
                    } else if (character.equals("c")) {
                        Ch.isAllowedByPrevious(previous);
                        previous = new Ch();
                        String value = expression.substring(lastSubStringStartIndex, x);
                        Ch.reverse(value, expression, x);

                        stack.push(Float.parseFloat(
                                expression.substring(lastSubStringStartIndex, x).replace(",", "")
                        ));
                    } else if (character.equals("-")) {
                        Subtraction.isAllowedByPrevious(previous);
                        previous = new Subtraction();
                        rightOperand = stack.pop();
                        leftOperand = stack.pop();

                        stack.push(leftOperand - rightOperand);
                        lastSubStringStartIndex = x;
                    } else if (character.equals(".")) {
                        Dot.isAllowedByPrevious(previous);
                        previous = new Dot();
                    } else if (character.equals(",")) {
                        Comma.isAllowedByPrevious(previous);
                        previous = new Comma();
                    } else {
                        throw new IOException("Invalid expression!");
                    }
                }
            }
        }

        return stack.pop();
    }

    private static String[] splitExpression(String expression) {
        return expression.split("");
    }

    private static void checkLengthValidity(String[] letters) throws IOException {
        if (letters.length < MIN_EXPRESSION_LENGTH) {
            throw new IOException("Invalid expression length");
        }
    }

    private static void checkIsNumeric(String character) throws IOException {
        try {
            Integer.parseInt(character);
        } catch (NumberFormatException e) {
            throw new IOException("Invalid expression!");
        }
    }

    private static boolean isCharacterValidOperator(String character) {
        return VALID_OPERATORS.contains(character);
    }

    private static boolean isFirstCharacter(int characterIndex) {
        return characterIndex == 0;
    }

    private static String getLetterByIndex(int characterIndex) {
        return letters[characterIndex];
    }
}
