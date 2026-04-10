package practice5.app;

import java.beans.Expression;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class EvalExpressions {
    public static void main(String[] args) {
        final String EXPRESSIONS_INPUT_PATH = "src/practice5/data/expressions.txt";
        final String RES_OUTPUT_PATH = "src/practice5/data/exp_res.txt";
        final String LOGS_OUTPUT_PATH = "src/practice5/data/exp_logs.txt";

        try (BufferedReader expReader = new BufferedReader(new FileReader(EXPRESSIONS_INPUT_PATH));
             PrintWriter resWriter = new PrintWriter(RES_OUTPUT_PATH);
             PrintWriter logsWriter = new PrintWriter(LOGS_OUTPUT_PATH);) {
            while (expReader.ready()) {
                String line = expReader.readLine();
                System.out.println(line);
                try {
                    double res = eval(line);
                    resWriter.println(res);
                    logsWriter.println("OK");
                    System.out.println("OK");
                } catch (NumberFormatException e) {
                    System.out.printf("Error: %s\n", e.getMessage());
                    logsWriter.println("NumberFormatException");
                } catch (UnsupportedOperationException e) {
                    System.out.printf("Error: %s\n", e.getMessage());
                    logsWriter.println("UnsupportedOperationException");
                } catch (NoSuchElementException e) {
                    System.out.printf("Error: %s\n", e.getMessage());
                    logsWriter.println("NoSuchElementException");
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    static public double eval(String exp) {
        exp = exp.replaceAll(" ", "");
        StringTokenizer st = new StringTokenizer(exp, "+-*/", true);

        String token = st.nextToken();
        double a;
        if (token.equals("-")) {
            a = Double.parseDouble(st.nextToken());
        } else {
            a = Double.parseDouble(token);
        }

        String op = st.nextToken();
        ArrayList<String> supporteds = new ArrayList<>(List.of(
                "+", "-", "*", "/"
        ));
        System.out.println(op.contentEquals(op));
        if (!op.contentEquals(op)) {
            throw new UnsupportedOperationException();
        }

        double b = Double.parseDouble(st.nextToken());
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
            default: throw new UnsupportedOperationException();
        }

    }
}
