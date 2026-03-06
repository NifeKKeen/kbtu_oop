package practice4_preferred.interfaces.services;

import practice4.problem4.NameComparator;
import practice4_preferred.interfaces.model.Cat;
import practice4_preferred.interfaces.model.IPhone;
import practice4_preferred.interfaces.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // P2
        Student s = new Student("s", 1);
        Cat c = new Cat();
        Restaurant r = new Restaurant();

        r.servePizza(s);
        r.servePizza(c);

        // P3
        App app = new App();
        app.getStatistics(new LogicGame());

        IPhone iphone = new IPhone();
        iphone.plug();
        iphone.sell();

        // P4
        ArrayList<practice4.problem4.Student> students = new ArrayList<>(
                List.of(
                        new practice4.problem4.Student("K", 3.33),
                        new practice4.problem4.Student("A", 3.66),
                        new practice4.problem4.Student("N", 0.97)
                )
        );

        Collections.sort(students);
        Collections.sort(students, new NameComparator());

        for (practice4.problem4.Student student : students) {
            System.out.println(student.getName());
        }

        // Bonus
        CountSort cs = new CountSort(0, 9);
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0,5,2,4,1,2,2,2,1,1,1,1,5,6,6,6,9};
        cs.sort(arr);
        for (int i = 0; i < arr.length; i++)
            System.out.printf("%d ", arr[i]);

    }
}
