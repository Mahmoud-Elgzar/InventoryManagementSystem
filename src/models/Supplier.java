package models;

public abstract class Supplier {
    private String name;

    // Constructor
    public Supplier(String name) {
        this.name = name;
    }

    // Getter
    public String getName() {
        return name;
    }

    // Abstract method for any specific supplier behavior
    public abstract String getSupplierType();
}
