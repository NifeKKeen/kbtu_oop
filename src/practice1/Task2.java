package practice1;

import java.util.Scanner;

public class Task2 {
    public static void solution(Scanner sc) {
        System.out.print("Input sqaure side length: ");
        double side = sc.nextDouble();
        System.out.printf("Area: %.4f\n", Math.pow(side, 2));
        System.out.printf("Perimeter: %.4f\n", 4 * side);
        System.out.printf("Diagonal: %.4f\n", Math.sqrt(2) * side);
    }
}
