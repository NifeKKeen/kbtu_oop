package practice5.app;

import practice5.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3App {
    private static final String LIBRARY_DATA_PATH = "src/practice5/data/library.dat";
    private static List<Book> library = new ArrayList<>();

    public static void main(String[] args) {
        loadData();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n(A)dd | (L)ist | (Q)uit");
            String choice = sc.nextLine().toUpperCase();
            if (choice.equalsIgnoreCase("Q")) {
                break;
            }

            if (choice.equalsIgnoreCase("A")) {
                System.out.print("Title: ");
                String t = sc.nextLine();
                System.out.print("Author: ");
                String a = sc.nextLine();
                library.add(new Book(t, a, 66669999));
            } else if (choice.equalsIgnoreCase("L")) {
                for (Book book : library) {
                    System.out.println(book);
                }
            }
        }
        saveData();
    }

    private static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LIBRARY_DATA_PATH))) {
            oos.writeObject(library);
            System.out.println("Saved");
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    private static void loadData() {
        File file = new File(LIBRARY_DATA_PATH);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(LIBRARY_DATA_PATH))) {
            library = (ArrayList<Book>) ois.readObject();
            System.out.println("Loaded books: " + library.size());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing data found or load error.");
        }
    }
}
