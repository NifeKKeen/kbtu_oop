package lab2.problem5.main;

import lab2.problem5.models.*;
import lab2.problem5.services.PersonRegistry;

public class PevTest {
    public static void main(String[] args) {
        Person john = new Employee("John", 30, 123);
        Person alice = new PhDStudent("Alice", 26, 2,"AI");
        Animal murka = new Cat("Murka");
        john.assignPet(murka); // John owns Rex

        PersonRegistry registry = new PersonRegistry();
        registry.addPerson(john);
        registry.addPerson(alice);
        // John goes on vacation and leaves Rex with Alice
        john.leavePetWith(alice);
        // Registry reflects that Alice is taking care of Rex
        System.out.println(registry);
        System.out.println("------------------------");
        // John returns from vacation and retrieves Rex
        john.retrievePetFrom(alice);
        // Registry reflects that John has his dog back
        System.out.println(registry);
        System.out.println("------------------------");
    }
}
