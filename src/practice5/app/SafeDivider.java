package practice5.app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SafeDivider {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        try {
            int x1 = scan.nextInt();
            int x2 = scan.nextInt();
            System.out.println(x1 / x2);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero.");
        } catch (InputMismatchException e) {
            System.out.println("Input mismatch: You entered text instead of an integer.");
        }

        System.out.println("Graceful end.");

        scan.close();
    }
}
