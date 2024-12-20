package models;

public class Furniture extends Product {

    public Furniture(String name, int quantity, double price) {
        super(name, "Furniture", quantity, price);
    }

    @Override
    public void displayDetails() {
        System.out.println("Furniture Product: " + getName() + ", Quantity: " + getQuantity() + ", Price: " + getPrice());
    }
}
