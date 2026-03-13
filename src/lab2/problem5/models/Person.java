package lab2.problem5.models;

public abstract class Person {
    protected String name;
    protected int age;
    protected Animal animal = null;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void assignPet(Animal animal) {
        this.animal = animal;
    }

    public void removePet() {
        this.animal = null;
    }

    public boolean hasPet() {
        return this.animal != null;
    }

    public Animal getPet() {
        return this.animal;
    }

    public void leavePetWith(Person o) {
        o.assignPet(this.animal);
        this.removePet();
    }
    public void retrievePetFrom(Person o) {
        this.assignPet(o.getPet());
        o.removePet();
    }

    public abstract String getOccupation();

    public abstract String toString();
    public abstract boolean equals(Object o);
    public abstract int hashCode();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
