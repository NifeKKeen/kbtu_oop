package practice2;

class Student {
    String name, id;
    int yearOfStudy = 1;

    Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void incrementYearOfStudy() {
        this.yearOfStudy++;
    }
}

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
