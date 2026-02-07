package practice2;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseStudent extends Student {
    private double grade = 0;
    public CourseStudent(String name, String id) {
        this(name, id, 0);
    }
    public CourseStudent(String name, String id, double grade) {
        super(name, id);
        this.grade = grade;
    }

    public static ArrayList<CourseStudent> inputStudents(int size) {
        ArrayList<CourseStudent> students = new ArrayList<>(size);
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            double grade;
            String studentLiteral = Character.toString(i + 65);
            do {
                System.out.printf("Enter grade for student %s: ", studentLiteral);
                try {
                    grade = sc.nextDouble();
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input, retry.");
                    sc.nextLine();
                }
            } while (true);

            students.add(new CourseStudent(studentLiteral, "", grade));
        }

        sc.close();
        return students;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
