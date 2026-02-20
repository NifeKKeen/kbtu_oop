package practice3.animal.model;

abstract public class Animal {
    protected String name;
    protected int age;
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void makeSound() {
        System.out.println("Sound from " + this.name);
    }
    public void eat() {
        System.out.println(this.name + "is eating...");
    }
    public String getInfo() {
        return String.format("Animal<%s>", this.getInfoProps());
    }
    public String getInfoProps() {
        return String.format("name=\"%s\",age=%d", name, age);
    }
}
