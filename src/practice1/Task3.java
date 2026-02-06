package practice1;

import java.util.Scanner;

public class Task3 {
    static String[] grades = { "F", "D", "D+", "C-", "C", "C+", "B-", "B", "B+", "A-", "A" };
    public static void solution(Scanner sc) {
        String formatString = "%s: %.2f\n";
        System.out.printf(formatString, grades[0], 0.);
        for (int i = 1; i < grades.length; i++) {
            double gpa = 1 + (1. / 3) * (i - 1);
            System.out.printf(formatString, grades[i], gpa);
        }
    }
}
