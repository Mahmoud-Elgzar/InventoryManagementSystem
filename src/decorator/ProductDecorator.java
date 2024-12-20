package decorator;

import models.Product;

public abstract class ProductDecorator extends Product {
    protected final Product product;

    // Update the constructor to include category as well
    public ProductDecorator(Product product) {
        super(product.getName(), product.getCategory(), product.getQuantity(), product.getPrice());
        this.product = product;
    }
}
