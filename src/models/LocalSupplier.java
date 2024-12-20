package models;

public class LocalSupplier extends Supplier {

    public LocalSupplier(String name) {
        super(name);
    }

    @Override
    public String getSupplierType() {
        return "Local";
    }
}
