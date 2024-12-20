package factories;

import models.Electronics;
import models.Furniture;
import models.Product;

public class ProductFactory {
    public static Product createProduct(String category, String name, int quantity, double price) {
        switch (category.toLowerCase()) {
            case "electronics":
                return new Electronics(name, quantity, price);
            case "furniture":
                return new Furniture(name, quantity, price);
            default:
                throw new IllegalArgumentException("Unknown category: " + category);
        }
    }
}
