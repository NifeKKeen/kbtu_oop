package practice4.problem2;

public class Student extends Person implements CanHavePizza, CanHaveRetake, Movable {
    public void eatPizza() {
        System.out.println("Student eats pizza");
    }

    public void retakeExam() {
        System.out.println("Press F");
    }

    public void move() {
        System.out.println("Student moves");
    }

    public void dance() {
        System.out.println("I like to move it move it");
    }
}
