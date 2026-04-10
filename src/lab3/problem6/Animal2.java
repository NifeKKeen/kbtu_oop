package lab3.problem6;

import lab2.problem5.models.Animal;

public abstract class Animal2 extends Animal implements Cloneable, Comparable<Animal> {
    public Animal2(String name) {
        super(name);
    }

    @Override
    public int compareTo(Animal o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
