package models;

public class InternationalSupplier extends Supplier {

    public InternationalSupplier(String name) {
        super(name);
    }

    @Override
    public String getSupplierType() {
        return "International";
    }
}
