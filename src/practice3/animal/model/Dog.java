package practice3.animal.model;

public class Dog extends Animal {
    protected String breed;
    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    public void makeSound() {
        System.out.println("'Bark!' from " + this.name);
    }
    public String getInfo() {
        return String.format("Dog<%s>", this.getInfoProps());
    }
    public String getInfoProps() {
        return super.getInfoProps() + String.format(",breed=\"%s\"", breed);
    }
}
