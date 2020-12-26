package gokhanustuner.test;

import gokhanustuner.product.RPN;
import java.io.IOException;

/**
 * BOUN SWE-510, 2020 Fall, Remedial class' Midterm I
 *
 * Tester is the class that executes some RPN test suites.
 *
 * @author Gökhan Üstüner
 **/
public class Tester {
    /**
     * Main method executing all test suites.
     *
     * @param args String[]
     * @throws IOException Throws
     */
    public static void main(String[] args) throws IOException {
        testAddition();
        testSubtractionAndMultiplicationCombined();
        testAdditionAndChangeSignCombined();
        testConsecutiveAddition();
        testDivisionAndAdditionCombined();

        System.out.println("Assertions are done.");
    }

    /**
     * Addition test suite
     *
     * @throws IOException Throws
     */
    private static void testAddition() throws IOException {
        RPN rpn = new RPN();
        rpn.apply("1");
        rpn.apply("4");

        assert rpn.apply("+") == 5.0;
    }

    /**
     * Combined subtraction and multiplication test suite
     *
     * @throws IOException Throws
     */
    private static void testSubtractionAndMultiplicationCombined() throws IOException {
        RPN rpn = new RPN();
        rpn.apply("745");
        rpn.apply("134");
        rpn.apply("543");

        assert rpn.apply("-") == -409;
        assert rpn.apply("*") == -304705;
    }

    /**
     * Combined change sign and addition test suite
     *
     * @throws IOException Throws
     */
    private static void testAdditionAndChangeSignCombined() throws IOException {
        RPN rpn = new RPN();
        rpn.apply("111");

        assert rpn.apply("c") == -111;
        assert rpn.apply("c") == 111;

        rpn.apply("c");
        rpn.apply("24");

        assert rpn.apply("+") == -87;
    }

    /**
     * Consecutive addition test suite
     *
     * @throws IOException Throws
     */
    private static void testConsecutiveAddition() throws IOException {
        RPN rpn = new RPN();
        rpn.apply("1");
        rpn.apply("2");
        rpn.apply("+");
        rpn.apply("3");
        rpn.apply("+");
        rpn.apply("4");

        assert rpn.apply("+") == 10;
    }

    /**
     * Combined division and addition test suite
     *
     * @throws IOException Throws
     */
    private static void testDivisionAndAdditionCombined() throws IOException {
        RPN rpn = new RPN();
        rpn.apply("6");
        rpn.apply("2");
        rpn.apply("/");
        rpn.apply("3");
        rpn.apply("+");
        rpn.apply("3");

        assert rpn.apply("/") == 2;
    }
}
