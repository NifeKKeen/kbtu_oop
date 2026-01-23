package lab1;

import java.util.Scanner;

public class Task5 {
    public static void solution(Scanner sc) {
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
}
