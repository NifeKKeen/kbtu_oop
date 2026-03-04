package practice4.problem2;

public class Main {
    public static void main(String[] args) {
        Student s = new Student();
        Cat c = new Cat();
        Restaurant r = new Restaurant();

        r.servePizza(s);
        r.servePizza(c);
    }
}
