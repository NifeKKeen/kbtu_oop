package practice3.animal.model;

public class Parrot extends Animal {
    Parrot(String name, int age) {
        super(name, age);
    }
    @Override
    public void eat() {
        System.out.println("parrot" + this.name + "is eating...");
    }

    void repeat(String phrase) {
        System.out.println("Parrot repeats ");
        System.out.println(phrase);
    }
}
