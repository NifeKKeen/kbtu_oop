package lab1;

import java.util.Scanner;

public class Task4 {
    public static void solution(Scanner sc) {
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
}
