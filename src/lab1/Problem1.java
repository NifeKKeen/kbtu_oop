package lab1;

import java.util.Locale;
import java.util.Scanner;

class Data {
    private static int counter = 0;
    private String uuid;
    int serialId;
    double x, y;
    int z;

    Data() {
        this.serialId = Data.counter++;
        this.uuid = "uuid-" + serialId;
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public void addValueX(double value) {
        this.x += value;
    }
    public void addValueY(double value) {
        this.y += value;
    }
    public void addValueZ(int value) {
        this.z += value;
    }

    public double getAverage() {
        return (x + y + z) / 3;
    }

    public double getMax() {
        return Math.max(Math.max(x, y), z);
    }
}

class Analyzer {
    public static void analyzeData() {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.UK);

        Data data = new Data();
        try {
            String input;
            System.out.print("Enter number(Q to quit): ");
            input = sc.next();
            if (input.equals("Q")) return;
            data.addValueX(Double.parseDouble(input));

            System.out.print("Enter number(Q to quit): ");
            input = sc.next();
            if (input.equals("Q")) return;
            data.addValueY(Double.parseDouble(input));

            System.out.print("Enter number(Q to quit): ");
            input = sc.next();
            if (input.equals("Q")) return;
            data.addValueZ(Integer.parseInt(input));

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
