import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Scanner;

public class Calculator {
    private final static String EXIT_COMMAND = "exit";

    public static void main(String[] args) {
        loop(new RPN());
        System.out.println("Good bye!!!");
    }

    private static void loop(RPN rpn) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your expression:");
        String expression = input.nextLine();

        if (! expression.equals(EXIT_COMMAND)) {
            try {
                System.out.println(rpn.apply(expression));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (EmptyStackException e) {
                System.out.println("Stack is empty.");
            }

            loop(rpn);
        }
    }
}
