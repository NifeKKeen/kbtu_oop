package lab1;

import java.util.Locale;
import java.util.Scanner;

public class Lab1 {
    static Scanner sc = null;
    public static void run() {
        Locale.setDefault(Locale.UK);
        // Locale.setDefault(Locale.US);
        // Locale.setDefault(Locale.FRANCE);
        // sc = new Scanner(System.in).useLocale(Locale.UK);
        sc = new Scanner(System.in);

        Task1.solution(sc);
        // Task1.solution("aaa");

        Task2.solution(sc);

        Task3.solution(sc);

        try {
            Task4.solution(sc);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }

        Task5.solution(sc);

        Task6.solution(sc);

        sc.close();
    }
}
