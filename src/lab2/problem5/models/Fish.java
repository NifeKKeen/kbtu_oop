package lab2.problem5.models;

public class Fish extends Animal {
    public Fish(String name) {
        super(name);
    }

    @Override
    public String getSound() {
        return "Bulk";
    }
}
