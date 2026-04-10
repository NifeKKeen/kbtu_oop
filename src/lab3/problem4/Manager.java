package lab3.problem4;

import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class Manager extends Employee {
    private Vector<Employee> team;
    private double bonus;

    public Manager(String name, int age, double salary, Date hireDate, String insuranceNumber, double bonus) {
        super(name, age, salary, hireDate, insuranceNumber);
        this.bonus = bonus;
        this.team = new Vector<>();
    }

    public void addEmployeeToTeam(Employee e) {
        team.add(e);
    }

    public double getBonus() {
        return bonus;
    }

    public Vector<Employee> getTeam() {
        return team;
    }

    @Override
    public String getOccupation() {
        return "Manager";
    }

    @Override
    public String toString() {
        return String.format("Manager<name=%s,age=%d,salary=%.2f,hireDate=%s,insuranceNumber=%s,bonus=%.2f,team=%s>",
                name, age, getSalary(), getHireDate(), getNationalInsuranceNumber(), bonus, team.toString());
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
        int salaryComparison = super.compareTo(o);
        if (salaryComparison == 0 && o instanceof Manager) {
            Manager m = (Manager) o;
            return Double.compare(this.bonus, m.bonus);
        }
        return salaryComparison;
    }

    @Override
    protected Manager clone() throws CloneNotSupportedException {
        Manager cloned = (Manager) super.clone();
        cloned.team = new Vector<>();
        for (Employee e : this.team) {
            cloned.team.add(e.clone());
        }
        return cloned;
    }
}
