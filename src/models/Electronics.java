package models;

public class Electronics extends Product {

    public Electronics(String name, int quantity, double price) {
        super(name, "Electronics", quantity, price);
    }

    @Override
    public void displayDetails() {
        System.out.println("Electronics Product: " + getName() + ", Quantity: " + getQuantity() + ", Price: " + getPrice());
    }
}
