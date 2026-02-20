package lab1;

import java.util.Locale;
import java.util.Scanner;

class Data {
    private double sum, mx;
    private int len;

    Data() {
        sum = len = 0;
        mx = Double.NEGATIVE_INFINITY;
    }

    public void addValue(double value) {
        this.sum += value;
        this.mx = Math.max(this.mx, value);
        this.len++;
    }

    public double getAverage() {
        return sum / len;
    }
    public double getMax() {
        return this.mx;
    }
}

class Analyzer {
    public static void analyzeData() {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.UK);

        Data data = new Data();
        try {
            while (true) {
                String input;
                System.out.print("Enter number(Q to quit): ");
                input = sc.next();
                if (input.equals("Q")) break;
                data.addValue(Double.parseDouble(input));
            }

            System.out.println("Average = " + data.getAverage());
            System.out.println("Max = " + data.getMax());
        } catch (Exception e) {
            System.out.println("Invalid input, skipping...");
        }
        sc.close();
    }
}

public class Problem1 {
    public static void solution() {
        Analyzer.analyzeData();
    }
}
