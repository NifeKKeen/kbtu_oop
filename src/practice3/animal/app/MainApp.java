package practice3.animal.app;

import practice3.animal.model.Animal;
import practice3.animal.model.Dog;

import java.util.ArrayList;

public class MainApp {
    public static void main(String[] args) {
        Animal a1 = new Dog("Karl", 12, "copper");
        Dog d1 = new Dog("Karl Second", 7, "diamond");
        Dog d2 = new Dog("Karl Third", 1, "gold");

        ArrayList<Animal> list = new ArrayList<Animal>();
        list.add(a1);
        list.add(d1);
        list.add(d2);

        for (Animal a : list) {
            System.out.println(a.getInfo());
        }
    }
}
