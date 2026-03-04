package practice4.problem4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>(
            List.of(
                    new Student("K", 3.33),
                    new Student("A", 3.66),
                    new Student("N", 0.97)
                )
        );

        // Collections.sort(students);
        Collections.sort(students, new NameComparator());

        for (Student student : students) {
            System.out.println(student.getName());
        }
    }
}
