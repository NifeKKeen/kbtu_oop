package practice4_preferred.interfaces.model;

public class Student
        extends Person
        implements CanHavePizza, CanHaveRetake, Movable, Comparable<Student>
{
    private String name;
    private double gpa;

    public Student(String name, double gpa) {
        super(name);
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public int compareTo(Student o) {
        return Double.compare(this.gpa, o.getGpa());
        // if (this.gpa < o.getGpa())
        //     return -1;
        // else if (this.gpa > o.getGpa())
        //     return 1;
        // else
        //     return 0;
    }

    @Override
    public void eatPizza() {
        System.out.println("Student eats pizza");
    }

    @Override
    public void retakeExam() {
        System.out.println("Press F");
    }

    @Override
    public void move() {
        System.out.println("Student moves");
    }

    @Override
    public void dance() {
        System.out.println("I like to move it move it");
    }
}
