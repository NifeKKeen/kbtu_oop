package practice5.app;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCountReporter {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> mp = new HashMap<>();

        String FILE_INPUT_PATH = "src/practice5/data/input.txt";
        String FILE_OUTPUT_PATH = "src/practice5/data/report.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_INPUT_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] words = getWords(line);
                for (String word : words) {
                    mp.put(word, (mp.get(word) == null ? 0 : mp.get(word)) + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(mp.entrySet());

        list.sort(Map.Entry.comparingByValue());
        list = list.reversed();

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : list) {
            sb.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }

        try (PrintWriter writer = new PrintWriter(FILE_OUTPUT_PATH)) {
            writer.print(sb);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    static public String[] getWords(String s) {
        String[] strs = s.split("\\W+");
        for (int i = 0; i < strs.length; i++) {
            strs[i] = strs[i].toLowerCase();
        }
        return strs;
    }
}
