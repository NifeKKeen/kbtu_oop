package lab2.problem5.models;

public class Student extends Person {
    protected int studyYear;
    public Student(String name, int age, int studyYear) {
        super(name, age);
        this.studyYear = studyYear;
    }

    @Override
    public String getOccupation() {
        return "Student";
    }

    @Override
    public String toString() {
        return String.format("Student<name=%s,age=%d,studyYear=%d>", name, age, studyYear);
    }

    @Override
    public boolean equals(Object o) {
        return o.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
