package practice2;

public class Task1 {
    public static void solution() {
        System.out.println("---- Task 1 ----");

        Student student = new Student("Kanich", "613372289");
        student.incrementYearOfStudy();
        System.out.println(student.getYearOfStudy());
        System.out.println(student.getName());
        System.out.println(student.getId());
    }
}
