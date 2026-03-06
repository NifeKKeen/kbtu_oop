package midterm;

import java.util.ArrayList;

public class Cart {
    ArrayList<Product> products;
    public Cart(ArrayList<Product> products) {
        this.products = products;
    }

    public void add(Product product) {
        this.products.add(product);
    }

    public boolean remove(Product product) {
        this.products.remove(product);
        // int idx = -1;
        // for (int i = 0; i < this.products.size(); i++) {
        //     if (this.products.get(i) == product) {
        //         idx = i;
        //     }
        // }
        return true;
    }

    public void print() {
        for (Product p : this.products) {
            System.out.printf("Name %s, price %.2f, quantity %d%n", p.name, p.price, p.quantity);
        }
    }
}
