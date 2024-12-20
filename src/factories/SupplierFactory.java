package factories;

import models.InternationalSupplier;
import models.LocalSupplier;
import models.Supplier;

public class SupplierFactory {

    // Factory method to create suppliers based on type
    public static Supplier createSupplier(String type, String name) {
        // Validate input parameters to ensure they are not null or empty
        if (type == null || type.trim().isEmpty() || name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Type and Name must not be null or empty");
        }

        // Using a switch expression to create the appropriate supplier
        switch (type.toLowerCase()) {
            case "local":
                return new LocalSupplier(name);
            case "international":
                return new InternationalSupplier(name);
            default:
                throw new IllegalArgumentException("Unknown supplier type: " + type);
        }
    }
}
