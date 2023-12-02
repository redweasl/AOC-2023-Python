import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CalibrationSum {

    // Basic idea: Use a scanner to read each line from a file
    // Start by finding the first digit in the line, then traverse in reverse to find the last digit
    // Assumes each line has at least two numbers in it
    public static int calculateSum(File f) throws FileNotFoundException {
        int sum = 0;
        Scanner s = new Scanner(f);

        // Functional, but definitely feels like there is a better designed solution here.
        while(s.hasNextLine()) {
            String line = s.nextLine();

            for (int i = 0; i < line.length(); i++) {
                if (isNumber(line.charAt(i))) {
                    int tens = Integer.parseInt("" + line.charAt(i));
                    sum = sum + (tens * 10);
                    break;
                }
            }

            for (int i = line.length() - 1; i >= 0; i--) {
                if (isNumber(line.charAt(i))) {
                    int ones = Integer.parseInt("" + line.charAt(i));
                    sum = sum + ones;
                    break;
                }
            }
        }

        s.close();
        return sum;
    }

    // Little method to tell me if a string (really character) is a number or not
    // I miss being able to just say Number? in Scheme
    public static boolean isNumber(char c) {
        try {
            Integer.parseInt("" + c);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("This program calculates the calibration number needed for the trebuchet.\n");

        System.out.println("Running part 1 test document...");
        File f = new File("documentTest.txt");
        int num = calculateSum(f);
        int answerExpected = 142;
        if (num != answerExpected) {
            throw new RuntimeException("\nTEST FAILED!\nExpected: " + answerExpected + "\nActual: " + num);
        }
        System.out.println("Expected answer of " + answerExpected + " received: TEST PASS\n");

        // System.out.println("Running part 2 test document...");
        // f = new File("documentPart2.txt");
        // num = calculateSum(f);
        // answerExpected = 281;
        // if (num != answerExpected) {
        //     throw new RuntimeException("\nTEST FAILED!\nExpected: " + answerExpected + "\nActual: " + num);
        // }
        // System.out.println("Expected answer of " + answerExpected + " received: TEST PASS\n");

        System.out.println("Running real document...");
        f = new File("document.txt");
        num = calculateSum(f);
        System.out.println("The calculated sum is " + num);
    }
}