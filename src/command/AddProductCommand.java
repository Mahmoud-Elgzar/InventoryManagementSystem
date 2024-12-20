package command;

import database.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Product;
import observer.Inventory;
import view.InventoryManagementApp;

public class AddProductCommand implements Command {
    private final String name;
    private final String category;
    private final int quantity;
    private final double price;

    public AddProductCommand(String name, String category, int quantity, double price) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public AddProductCommand(InventoryManagementApp aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void execute() {
        String insertQuery = "INSERT INTO products (name, category, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(insertQuery)) {
            stmt.setString(1, name);
            stmt.setString(2, category);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, price);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
