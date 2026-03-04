package practice4.problem2;

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
