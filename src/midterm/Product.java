package midterm;

public class Product {
    String name;
    double price;
    int quantity;

    public enum Category {
        UNKNOWN, TECH, FOOD, FURNITURE
    }
    Category category;

    public Product(String name, double price) {
        this(name, price, 1);
    }

    public Product(String name, double price, int quantity) {
        this(name, price, quantity, Category.UNKNOWN);
    }

    public Product(String name, double price, int quantity, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }
}
