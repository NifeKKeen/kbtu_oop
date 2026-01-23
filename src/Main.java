import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner sc = null;
    private static void task1(String textToDisplay) {
        int len = textToDisplay.length();
        System.out.println("+" + "-".repeat(len) + "+");
        System.out.println("|" + textToDisplay + "|");
        System.out.println("+" + "-".repeat(len) + "+");
    }

    private static void task2() {
        System.out.print("Input sqaure side length: ");
        double side = sc.nextDouble();
        // System.out.printf(Locale.US, "Area: %.4f\n", Math.pow(side, 2));
        // System.out.printf(Locale.US, "Perimeter: %.4f\n", 4 * side);
        // System.out.printf(Locale.US, "Diagonal: %.4f\n", Math.sqrt(2) * side);
        System.out.printf("Area: %.4f\n", Math.pow(side, 2));
        System.out.printf("Perimeter: %.4f\n", 4 * side);
        System.out.printf("Diagonal: %.4f\n", Math.sqrt(2) * side);
    }

    private static void task3() {
        String[] grades = { "F", "D", "D+", "C-", "C", "C+", "B-", "B", "B+", "A-", "A" };
        String formatString = "%s: %.2f\n";

        System.out.printf(formatString, grades[0], 0.);
        for (int i = 1; i < grades.length; i++) {
            double gpa = 1 + (1. / 3) * (i - 1);
            System.out.printf(formatString, grades[i], gpa);
        }
    }

    private static void task4() {
        System.out.println("You have polynomial ax^2 + bx + c = 0");
        System.out.print("Input coefficients a, b, c: ");
        double a = sc.nextDouble(), b = sc.nextDouble(), c = sc.nextDouble();
        double d = Math.pow(b, 2) - 4 * a * c;
        if (d < 0) {
            throw new Error("No real roots");
        } else if (d < 1e-9) {
            System.out.printf("x1 = x2 = %.2f\n", -b / (2 * a));
        } else {
            System.out.printf("x1 = %.2f, x2 = %.2f\n", (-b + Math.sqrt(d)) / (2 * a), (-b - Math.sqrt(d)) / (2 * a));
        }
    }

    private static void task5() {
        System.out.print("Input initial account balance: ");
        double balance = sc.nextDouble();

        System.out.print("Input interest rate: ");
        double interest = sc.nextDouble();

        System.out.print("How many years to simulate?: ");
        int years = sc.nextInt();

        for (int i = 0; i < years; i++) {
            balance *= (1 + interest);
            System.out.printf("Year %d: %.3f\n", i + 1, balance);
        }
    }

    private static void task6() {
        System.out.print("Input string to check if it palindrome: ");
        String input = sc.next();
        boolean isPalindrome = true;
        for (int i = 0; i < input.length() >> 1; i++) {
            if (input.charAt(i) != input.charAt(input.length() - i - 1)) {
                isPalindrome = false;
                break;
            }
        }
        System.out.printf("The text you input is %s\n", isPalindrome ? "palindrome" : "NOT palindrome");
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.UK);
        Locale.setDefault(Locale.US);
        // Locale.setDefault(Locale.FRANCE);
        // sc = new Scanner(System.in).useLocale(Locale.UK);
        sc = new Scanner(System.in);

        task1("aaa");
        // task1("Kanich");

        task2();

        task3();

        task4();

        task5();

        task6();
    }
}
