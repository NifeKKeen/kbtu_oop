package lab3.problem6;

import lab2.problem5.models.Animal;
import lab2.problem5.models.Person;

public abstract class Person2 extends Person implements Comparable<Person2>, Cloneable, Socializer, Feedable {
    public Person2(String name, int age) {
        super(name, age);
    }

    @Override
    public int compareTo(Person2 o) {
        return Integer.compare(this.age, o.age);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
