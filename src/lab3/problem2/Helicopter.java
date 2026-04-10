package lab3.problem2;

public class Helicopter implements Flyable {
    @Override
    public void move() {
        System.out.println("The helicopter moves.");
    }

    @Override
    public void fly() {
        System.out.println("The helicopter flies away.");
    }
}
