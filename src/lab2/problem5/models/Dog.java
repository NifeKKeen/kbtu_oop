package lab2.problem5.models;

public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public String getSound() {
        return "Nuridin loh";
    }
}
