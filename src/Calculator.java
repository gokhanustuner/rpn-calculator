import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * BOUN SWE-510, 2020 Fall, Remedial class' Midterm I
 *
 * Calculator is the class that contains a main method, starting point of the application,
 * and a loop method controlling the flow.
 *
 * @author Gökhan Üstüner
 **/
public class Calculator {

    /**
     * Constant for the special user input "exit"
     */
    private final static String EXIT_COMMAND = "exit";

    /**
     * The main method of the program
     * @param  args  String[]
     */
    public static void main(String[] args) {
        loop(new RPN());
        System.out.println("Good bye!!!");
    }

    /**
     * The method that is responsible to control the flow.
     * Passes the user input RPN class' apply() method.
     * Depending on the output, prints messages to the console.
     * Exits if the special "exit" expression is sent by the user, from the console.
     * Calls itself recursively otherwise.
     *
     * @param rpn instance of RPN class
     */
    private static void loop(RPN rpn) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your operand/operator and press enter:");
        String expression = input.nextLine();

        // Here, we check whether the user input is special "exit" command.
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
