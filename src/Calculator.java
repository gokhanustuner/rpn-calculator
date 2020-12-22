import java.io.IOException;
import java.util.Scanner;

public class Calculator {
    private final static String EXIT_COMMAND = "exit";

    public static void main(String[] args) {
        loop();
        System.out.println("Good bye!!!");
    }

    private static void loop() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your expression:");
        String expression = input.nextLine();

        if (! expression.equals(EXIT_COMMAND)) {
            try {
                System.out.println("The result is: " + RPN.apply(expression));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            loop();
        }
    }
}
