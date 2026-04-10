package lab3.problem4;

import java.util.Collections;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employee e1 = new Employee("Bob", 25, 50000, new Date(120, 0, 1), "NI123");
        Employee e2 = new Employee("Alice", 30, 60000, new Date(121, 5, 15), "NI456");

        Manager m1 = new Manager("Charlie", 40, 80000, new Date(115, 2, 10), "NI789", 5000);
        m1.addEmployeeToTeam(e1);
        m1.addEmployeeToTeam(e2);

        Manager m2 = m1.clone();

        System.out.println("M1 == M2? " + (m1 == m2)); // false
        System.out.println("M1.equals(M2)? " + (m1.equals(m2))); // true

        m2.setName("Clone Charlie");

        System.out.println("M1 == M2? " + (m1 == m2)); // false
        System.out.println("M1.equals(M2)? " + (m1.equals(m2))); // false

        System.out.println("M1 Team == M2 Team? " + (m1.getTeam() == m2.getTeam())); // false (deep cloned)
        System.out.println("Comparisons working: " + (e2.compareTo(e1) > 0)); // true, 60k > 50k

        Collections.sort(m1.getTeam(), new EmployeeHireDateComparator());
        System.out.println(m1.getTeam());

        Collections.sort(m2.getTeam(), new EmployeeNameComparator());
        System.out.println(m2.getTeam());
    }
}
