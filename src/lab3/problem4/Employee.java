package lab3.problem4;

import lab2.problem5.models.Person;

import java.util.Date;
import java.util.Objects;

public class Employee extends Person implements Comparable<Employee>, Cloneable {
    private double salary;
    private Date hireDate;
    private String insuranceNumber;

    public Employee(String name, int age, double salary, Date hireDate, String niNumber) {
        super(name, age);
        this.salary = salary;
        this.hireDate = hireDate;
        this.insuranceNumber = niNumber;
    }

    public String getOccupation() {
        return "Employee";
    }

    @Override
    public String toString() {
        return String.format("Employee<name=%s,age=%d,salary=%.2f,hireDate=%s,insuranceNumber=%s>",
                name, age, salary, hireDate, insuranceNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        return o.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
    @Override
    public int compareTo(Employee o) {
        return Double.compare(this.salary, o.salary);
    }

    @Override
    protected Employee clone() throws CloneNotSupportedException {
        Employee cloned = (Employee) super.clone();
        cloned.hireDate = (Date) this.hireDate.clone();
        return cloned;
    }

    public double getSalary() {
        return salary;
    }
    public Date getHireDate() {
        return hireDate;
    }
    public String getNationalInsuranceNumber() {
        return insuranceNumber;
    }
}
