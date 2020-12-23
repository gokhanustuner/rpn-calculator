import java.io.IOException;

/**
 * BOUN SWE-510, 2020 Fall, Remedial class' Midterm I
 *
 * Tester is the class that executes some RPN test suites.
 *
 * @author Gökhan Üstüner
 **/
public class Tester {
    public static void main(String[] args) throws IOException {
        RPN rpn = new RPN();
        rpn.apply("1");
        rpn.apply("4");
        assert rpn.apply("+") == 5.0;

        rpn = new RPN();
        rpn.apply("745");
        rpn.apply("134");
        rpn.apply("543");
        assert rpn.apply("-") == -409;
        assert rpn.apply("*") == -304705;

        rpn = new RPN();
        rpn.apply("111");
        assert rpn.apply("c") == -111;
        assert rpn.apply("c") == 111;
        rpn.apply("c");
        rpn.apply("24");
        assert rpn.apply("+") == -87;

        rpn = new RPN();
        rpn.apply("1");
        rpn.apply("2");
        rpn.apply("+");
        rpn.apply("3");
        rpn.apply("+");
        rpn.apply("4");
        assert rpn.apply("+") == 10;
    }
}
