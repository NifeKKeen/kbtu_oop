package lab1;

import java.util.Scanner;

public class Task6 {
    public static boolean isPalindrome(String s) {
        boolean isPalindrome = true;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }

    public static void solution(Scanner sc) {
        System.out.print("Input string to check if it palindrome: ");
        String text = sc.next();
        boolean isPalindrome = isPalindrome(text);
        System.out.printf("The text you input is %s\n", isPalindrome ? "palindrome" : "NOT palindrome");
    }
}
