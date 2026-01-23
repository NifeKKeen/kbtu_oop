package lab1;

import java.util.Scanner;

public class Task1 {
    public static void solution(Scanner sc) {
        System.out.print("Input text to display: ");
        String textToDisplay = sc.next();

        int len = textToDisplay.length();
        System.out.println("+" + "-".repeat(len) + "+");
        System.out.println("|" + textToDisplay + "|");
        System.out.println("+" + "-".repeat(len) + "+");
    }
}
