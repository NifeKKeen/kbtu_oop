package lab2.problem5.models;

public class Employee extends Person {
    private double salary;
    public Employee(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    @Override
    public String getOccupation() {
        return "Employee";
    }

    @Override
    public String toString() {
        return String.format("Employee<name=%s,age=%d,salary=%.2f>", name, age, salary);
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
