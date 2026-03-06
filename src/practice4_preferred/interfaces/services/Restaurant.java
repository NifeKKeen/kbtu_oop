package practice4_preferred.interfaces.services;

import practice4_preferred.interfaces.model.CanHavePizza;
import practice4_preferred.interfaces.model.Person;

public class Restaurant {
    public boolean servePizza(CanHavePizza eater) {
        eater.eatPizza();
        if (eater instanceof Person) {
            System.out.println("Processing payment...");
            System.out.println("Some person pays for a pizza");
        }
        return true;
    }
}
