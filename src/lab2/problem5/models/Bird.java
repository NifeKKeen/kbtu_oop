package lab2.problem5.models;

public class Bird extends Animal {
    public Bird(String name) {
        super(name);
    }

    @Override
    public String getSound() {
        return "Ks-ks-chik-chik-mpuk-mpuk-ahh";
    }
}
