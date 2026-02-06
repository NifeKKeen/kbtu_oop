package practice2;

class StarTriangle {
    int width;
    StarTriangle(int width) {
        if (width < 0) {
            throw new IllegalArgumentException("Width must be non-negative");
        }
        this.width = width;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < width; i++) {
            res .append("[*]".repeat(i + 1));
            res.append("\n");
        }
        return res.toString();
    }
}

public class Task2 {
    public static void solution() {
        System.out.println("---- Task 2 ----");
        System.out.println(new StarTriangle(5));
        // System.out.println(new StarTriangle(0));
    }
}
