package midterm;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> arr = new ArrayList<Product>(
                List.of(
                        new Product("a", 1.1, 1, Product.Category.FOOD),
                        new Product("b", 1.2, 1, Product.Category.TECH),
                        new Product("c", 1.3, 1, Product.Category.FURNITURE),
                        new Product("d", 1.4, 1, Product.Category.UNKNOWN)
                )
        );
        Cart c = new Cart(arr);

        c.print();
    }
}
