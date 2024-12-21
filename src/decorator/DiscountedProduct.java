package decorator;

import models.Product;

public class DiscountedProduct extends ProductDecorator {
    private final double discountRate;

    public DiscountedProduct(Product product, double discountRate) {
        super(product);
        this.discountRate = discountRate;
    }

    @Override
    public double getPrice() {
        return product.getPrice() * (1 - discountRate);
    }

    @Override
    public String getCategory() {
        return product.getCategory();
    }

    @Override
    public void displayDetails() {
            product.displayDetails();
    System.out.println("Discount Rate: " + (discountRate * 100) + "%");
    System.out.println("Discounted Price: $" + getPrice());
    }
}
