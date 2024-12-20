import view.InventoryManagementApp;

public class Main {
    public static void main(String[] args) {
        new InventoryManagementApp().setVisible(true);
    }
}

/*
import database.DatabaseConnection;
import models.Product;
import factories.ProductFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryManagementApp extends JFrame {

    private JTextField nameField, quantityField, priceField, categoryField;
    private JTextArea inventoryArea;

    public InventoryManagementApp() {
        setTitle("Inventory Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel nameLabel = new JLabel("Product Name:");
        nameField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();
        JLabel categoryLabel = new JLabel("Category:");
        categoryField = new JTextField();

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(categoryLabel);
        panel.add(categoryField);

        JButton addButton = new JButton("Add Product");
        JButton showButton = new JButton("Show Inventory");
        panel.add(addButton);
        panel.add(showButton);

        inventoryArea = new JTextArea(10, 30);
        inventoryArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(inventoryArea);

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Add product button action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        // Show inventory button action
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInventory();
            }
        });
    }

    private void addProduct() {
        String name = nameField.getText();
        String category = categoryField.getText();
        String quantityStr = quantityField.getText();
        String priceStr = priceField.getText();

        if (name.isEmpty() || category.isEmpty() || quantityStr.isEmpty() || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!");
            return;
        }

        int quantity;
        double price;
        try {
            quantity = Integer.parseInt(quantityStr);
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for quantity and price!");
            return;
        }

        // Create product using the factory pattern
        Product product = ProductFactory.createProduct(category, name, quantity, price);

        // Save product to database
        String insertQuery = "INSERT INTO products (name, category, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(insertQuery)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setInt(3, product.getQuantity());
            stmt.setDouble(4, product.getPrice());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding product: " + e.getMessage());
        }
    }

    private void showInventory() {
        inventoryArea.setText("");
        String selectQuery = "SELECT * FROM products";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(selectQuery);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                String category = rs.getString("category");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                inventoryArea.append("Name: " + name + ", Category: " + category + ", Quantity: " + quantity + ", Price: " + price + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching inventory: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InventoryManagementApp app = new InventoryManagementApp();
            app.setVisible(true);
        });
    }
}
*/


/*import database.DatabaseConnection;
import models.Product;
import models.Supplier;
import models.User;
import models.Order;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class InventoryManagementApp extends JFrame {

    private JTextField nameField, quantityField, priceField, categoryField;
    private JTextArea inventoryArea;
    private JTextField supplierNameField, supplierTypeField, contactField, phoneField, emailField;
    private JTextField orderProductField, orderQuantityField, totalPriceField;
    private JTextField usernameField, roleField;
    private JPasswordField passwordField;
    private JTable productTable, supplierTable, orderTable;

    public InventoryManagementApp() {
        setTitle("Inventory Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Product Tab
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(6, 2));
        JLabel nameLabel = new JLabel("Product Name:");
        nameField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();
        JLabel categoryLabel = new JLabel("Category:");
        categoryField = new JTextField();

        productPanel.add(nameLabel);
        productPanel.add(nameField);
        productPanel.add(quantityLabel);
        productPanel.add(quantityField);
        productPanel.add(priceLabel);
        productPanel.add(priceField);
        productPanel.add(categoryLabel);
        productPanel.add(categoryField);

        JButton addProductButton = new JButton("Add Product");
        JButton showInventoryButton = new JButton("Show Inventory");
        productPanel.add(addProductButton);
        productPanel.add(showInventoryButton);

        inventoryArea = new JTextArea(10, 30);
        JScrollPane productScrollPane = new JScrollPane(inventoryArea);
        productPanel.add(productScrollPane);

        tabbedPane.addTab("Products", productPanel);

        // Supplier Tab
        JPanel supplierPanel = new JPanel();
        supplierPanel.setLayout(new GridLayout(6, 2));
        JLabel supplierNameLabel = new JLabel("Supplier Name:");
        supplierNameField = new JTextField();
        JLabel supplierTypeLabel = new JLabel("Supplier Type (local/international):");
        supplierTypeField = new JTextField();
        JLabel contactLabel = new JLabel("Contact Person:");
        contactField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        supplierPanel.add(supplierNameLabel);
        supplierPanel.add(supplierNameField);
        supplierPanel.add(supplierTypeLabel);
        supplierPanel.add(supplierTypeField);
        supplierPanel.add(contactLabel);
        supplierPanel.add(contactField);
        supplierPanel.add(phoneLabel);
        supplierPanel.add(phoneField);
        supplierPanel.add(emailLabel);
        supplierPanel.add(emailField);

        JButton addSupplierButton = new JButton("Add Supplier");
        JButton showSuppliersButton = new JButton("Show Suppliers");
        supplierPanel.add(addSupplierButton);
        supplierPanel.add(showSuppliersButton);

        tabbedPane.addTab("Suppliers", supplierPanel);

        // User Tab
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(4, 2));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JLabel roleLabel = new JLabel("Role (admin/staff):");
        roleField = new JTextField();

        userPanel.add(usernameLabel);
        userPanel.add(usernameField);
        userPanel.add(passwordLabel);
        userPanel.add(passwordField);
        userPanel.add(roleLabel);
        userPanel.add(roleField);

        JButton addUserButton = new JButton("Add User");
        JButton showUsersButton = new JButton("Show Users");
        userPanel.add(addUserButton);
        userPanel.add(showUsersButton);

        tabbedPane.addTab("Users", userPanel);

        // Order Tab
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new GridLayout(5, 2));
        JLabel orderProductLabel = new JLabel("Product ID:");
        orderProductField = new JTextField();
        JLabel orderQuantityLabel = new JLabel("Quantity:");
        orderQuantityField = new JTextField();
        JLabel totalPriceLabel = new JLabel("Total Price:");
        totalPriceField = new JTextField();
        totalPriceField.setEditable(false);

        orderPanel.add(orderProductLabel);
        orderPanel.add(orderProductField);
        orderPanel.add(orderQuantityLabel);
        orderPanel.add(orderQuantityField);
        orderPanel.add(totalPriceLabel);
        orderPanel.add(totalPriceField);

        JButton createOrderButton = new JButton("Create Order");
        orderPanel.add(createOrderButton);

        tabbedPane.addTab("Orders", orderPanel);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Add action listeners for buttons
        addProductButton.addActionListener(e -> addProduct());
        showInventoryButton.addActionListener(e -> showInventory());
        addSupplierButton.addActionListener(e -> addSupplier());
        showSuppliersButton.addActionListener(e -> showSuppliers());
        addUserButton.addActionListener(e -> addUser());
        showUsersButton.addActionListener(e -> showUsers());
        createOrderButton.addActionListener(e -> createOrder());
    }

    // Method to add Product
    private void addProduct() {
        String name = nameField.getText();
        String category = categoryField.getText();
        String quantityStr = quantityField.getText();
        String priceStr = priceField.getText();

        if (name.isEmpty() || category.isEmpty() || quantityStr.isEmpty() || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!");
            return;
        }

        int quantity;
        double price;
        try {
            quantity = Integer.parseInt(quantityStr);
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for quantity and price!");
            return;
        }

        String insertQuery = "INSERT INTO products (name, category, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(insertQuery)) {
            stmt.setString(1, name);
            stmt.setString(2, category);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, price);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product added successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding product: " + e.getMessage());
        }
    }

    // Method to show Inventory
    private void showInventory() {
        inventoryArea.setText("");
        String selectQuery = "SELECT * FROM products";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(selectQuery);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                String category = rs.getString("category");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                inventoryArea.append("Name: " + name + ", Category: " + category + ", Quantity: " + quantity + ", Price: " + price + "\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching inventory: " + e.getMessage());
        }
    }

    // Method to add Supplier
    private void addSupplier() {
        String name = supplierNameField.getText();
        String type = supplierTypeField.getText();
        String contact = contactField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        if (name.isEmpty() || type.isEmpty() || contact.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!");
            return;
        }

        String insertQuery = "INSERT INTO suppliers (name, type, contact_person, phone, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(insertQuery)) {
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setString(3, contact);
            stmt.setString(4, phone);
            stmt.setString(5, email);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Supplier added successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding supplier: " + e.getMessage());
        }
    }

    // Method to show Suppliers
    private void showSuppliers() {
        String selectQuery = "SELECT * FROM suppliers";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(selectQuery);
             ResultSet rs = stmt.executeQuery()) {

            StringBuilder suppliersList = new StringBuilder();
            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                suppliersList.append("Name: ").append(name).append(", Type: ").append(type).append("\n");
            }

            JOptionPane.showMessageDialog(this, suppliersList.toString());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching suppliers: " + e.getMessage());
        }
    }

    // Method to add User
    private void addUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String role = roleField.getText();

        if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!");
            return;
        }

        String insertQuery = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(insertQuery)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "User added successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding user: " + e.getMessage());
        }
    }

    // Method to show Users
    private void showUsers() {
        String selectQuery = "SELECT * FROM users";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(selectQuery);
             ResultSet rs = stmt.executeQuery()) {

            StringBuilder usersList = new StringBuilder();
            while (rs.next()) {
                String username = rs.getString("username");
                String role = rs.getString("role");
                usersList.append("Username: ").append(username).append(", Role: ").append(role).append("\n");
            }

            JOptionPane.showMessageDialog(this, usersList.toString());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching users: " + e.getMessage());
        }
    }

    // Method to create Order
    private void createOrder() {
        String productIdStr = orderProductField.getText();
        String quantityStr = orderQuantityField.getText();

        if (productIdStr.isEmpty() || quantityStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!");
            return;
        }

        int productId, quantity;
        try {
            productId = Integer.parseInt(productIdStr);
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for product ID and quantity!");
            return;
        }

        // Query to get product details
        String selectProductQuery = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(selectProductQuery)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int availableQuantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                if (availableQuantity < quantity) {
                    JOptionPane.showMessageDialog(this, "Insufficient stock!");
                    return;
                }

                // Calculate total price and update stock
                double totalPrice = price * quantity;
                totalPriceField.setText(String.format("%.2f", totalPrice));

                // Update product quantity
                String updateProductQuery = "UPDATE products SET quantity = quantity - ? WHERE id = ?";
                try (PreparedStatement updateStmt = DatabaseConnection.getInstance().getConnection().prepareStatement(updateProductQuery)) {
                    updateStmt.setInt(1, quantity);
                    updateStmt.setInt(2, productId);
                    updateStmt.executeUpdate();
                }

                // Add order
                String insertOrderQuery = "INSERT INTO orders (product_id, quantity, total_price) VALUES (?, ?, ?)";
                try (PreparedStatement insertStmt = DatabaseConnection.getInstance().getConnection().prepareStatement(insertOrderQuery)) {
                    insertStmt.setInt(1, productId);
                    insertStmt.setInt(2, quantity);
                    insertStmt.setDouble(3, totalPrice);
                    insertStmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Order created successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Product not found!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error creating order: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InventoryManagementApp().setVisible(true);
        });
    }
}*/

/*import command.AddProductCommand;
import command.Command;
import command.CommandInvoker;
import decorator.DiscountedProduct;
import factories.ProductFactory;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import models.Product;
import observer.Inventory;
import observer.Observer;

public class InventoryManagementApp extends JFrame implements Observer {
    private JTable productTable;
    private Inventory inventory;
    private CommandInvoker commandInvoker;

    public InventoryManagementApp() {
        inventory = new Inventory();
        commandInvoker = new CommandInvoker();

        // Initialize JFrame settings
        setTitle("Inventory System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window on screen

        // Table to display products
        productTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Category");
        model.addColumn("Quantity");
        model.addColumn("Price");
        productTable.setModel(model);
        JScrollPane scrollPane = new JScrollPane(productTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Add Observer
        inventory.addObserver(this);

        // Panel for input fields and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // Fields for product data
        JTextField nameField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField priceField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);

        // Button to add product
        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String category = categoryField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());

            // Create product and command
            Product product = ProductFactory.createProduct(name, category, quantity, price);
            Command addProductCommand = new AddProductCommand(product, inventory);
            commandInvoker.setCommand(addProductCommand);
            commandInvoker.executeCommand();
        });

        panel.add(addButton);

        // Discount button
        JButton discountButton = new JButton("Apply Discount");
        discountButton.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow != -1) {
                String name = (String) productTable.getValueAt(selectedRow, 0);
                Product product = inventory.getProducts().stream()
                        .filter(p -> p.getName().equals(name))
                        .findFirst().orElse(null);

                if (product != null) {
                    Product discountedProduct = new DiscountedProduct(product, 0.1);  // 10% discount
                    productTable.setValueAt(discountedProduct.getPrice(), selectedRow, 3);
                }
            }
        });

        panel.add(discountButton);
        getContentPane().add(panel, BorderLayout.SOUTH);
    }

    
    public void update() {
        // Update the product table whenever the inventory changes
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0);  // Clear existing rows

        for (Product product : inventory.getProducts()) {
            model.addRow(new Object[]{product.getName(), product.getCategory(), product.getQuantity(), product.getPrice()});
        }
    }

    public static void main(String[] args) {
        // Create and display the GUI
        SwingUtilities.invokeLater(() -> new InventoryManagementApp().setVisible(true));
    }

    @Override
    public void update(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}*/

/*import database.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class InventoryManagementApp extends JFrame {

    private JTextField nameField, quantityField, priceField, categoryField;
    private JTextArea inventoryArea;
    private JTextField supplierNameField, supplierTypeField, contactField, phoneField, emailField;
    private JTextField orderProductField, orderQuantityField, totalPriceField;
    private JTextField usernameField, roleField;
    private JPasswordField passwordField;
    private JTable productTable, supplierTable, orderTable;

    public InventoryManagementApp() {
        setTitle("Inventory Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Product Tab
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(6, 2));
        JLabel nameLabel = new JLabel("Product Name:");
        nameField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();
        JLabel categoryLabel = new JLabel("Category:");
        categoryField = new JTextField();

        productPanel.add(nameLabel);
        productPanel.add(nameField);
        productPanel.add(quantityLabel);
        productPanel.add(quantityField);
        productPanel.add(priceLabel);
        productPanel.add(priceField);
        productPanel.add(categoryLabel);
        productPanel.add(categoryField);

        JButton addProductButton = new JButton("Add Product");
        JButton showInventoryButton = new JButton("Show Inventory");
        productPanel.add(addProductButton);
        productPanel.add(showInventoryButton);

        inventoryArea = new JTextArea(10, 30);
        JScrollPane productScrollPane = new JScrollPane(inventoryArea);
        productPanel.add(productScrollPane);

        tabbedPane.addTab("Products", productPanel);

        // Supplier Tab
        JPanel supplierPanel = new JPanel();
        supplierPanel.setLayout(new GridLayout(6, 2));
        JLabel supplierNameLabel = new JLabel("Supplier Name:");
        supplierNameField = new JTextField();
        JLabel supplierTypeLabel = new JLabel("Supplier Type (local/international):");
        supplierTypeField = new JTextField();
        JLabel contactLabel = new JLabel("Contact Person:");
        contactField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        supplierPanel.add(supplierNameLabel);
        supplierPanel.add(supplierNameField);
        supplierPanel.add(supplierTypeLabel);
        supplierPanel.add(supplierTypeField);
        supplierPanel.add(contactLabel);
        supplierPanel.add(contactField);
        supplierPanel.add(phoneLabel);
        supplierPanel.add(phoneField);
        supplierPanel.add(emailLabel);
        supplierPanel.add(emailField);

        JButton addSupplierButton = new JButton("Add Supplier");
        JButton showSuppliersButton = new JButton("Show Suppliers");
        supplierPanel.add(addSupplierButton);
        supplierPanel.add(showSuppliersButton);

        tabbedPane.addTab("Suppliers", supplierPanel);

        // User Tab
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(4, 2));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JLabel roleLabel = new JLabel("Role (admin/staff):");
        roleField = new JTextField();

        userPanel.add(usernameLabel);
        userPanel.add(usernameField);
        userPanel.add(passwordLabel);
        userPanel.add(passwordField);
        userPanel.add(roleLabel);
        userPanel.add(roleField);

        JButton addUserButton = new JButton("Add User");
        JButton showUsersButton = new JButton("Show Users");
        userPanel.add(addUserButton);
        userPanel.add(showUsersButton);

        tabbedPane.addTab("Users", userPanel);

        // Order Tab
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new GridLayout(5, 2));
        JLabel orderProductLabel = new JLabel("Product ID:");
        orderProductField = new JTextField();
        JLabel orderQuantityLabel = new JLabel("Quantity:");
        orderQuantityField = new JTextField();
        JLabel totalPriceLabel = new JLabel("Total Price:");
        totalPriceField = new JTextField();
        totalPriceField.setEditable(false);

        orderPanel.add(orderProductLabel);
        orderPanel.add(orderProductField);
        orderPanel.add(orderQuantityLabel);
        orderPanel.add(orderQuantityField);
        orderPanel.add(totalPriceLabel);
        orderPanel.add(totalPriceField);

        JButton createOrderButton = new JButton("Create Order");
        orderPanel.add(createOrderButton);

        tabbedPane.addTab("Orders", orderPanel);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Add action listeners for buttons
        addProductButton.addActionListener(e -> addProduct());
        showInventoryButton.addActionListener(e -> showInventory());
        addSupplierButton.addActionListener(e -> addSupplier());
        showSuppliersButton.addActionListener(e -> showSuppliers());
        addUserButton.addActionListener(e -> addUser());
        showUsersButton.addActionListener(e -> showUsers());
        createOrderButton.addActionListener(e -> createOrder());
    }

    // Method to add Product
    private void addProduct() {
        String name = nameField.getText();
        String category = categoryField.getText();
        String quantityStr = quantityField.getText();
        String priceStr = priceField.getText();

        if (name.isEmpty() || category.isEmpty() || quantityStr.isEmpty() || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!");
            return;
        }

        int quantity;
        double price;
        try {
            quantity = Integer.parseInt(quantityStr);
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for quantity and price!");
            return;
        }

        String insertQuery = "INSERT INTO products (name, category, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(insertQuery)) {
            stmt.setString(1, name);
            stmt.setString(2, category);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, price);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product added successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding product: " + e.getMessage());
        }
    }

    // Method to show Inventory
    private void showInventory() {
        inventoryArea.setText("");
        String selectQuery = "SELECT * FROM products";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(selectQuery);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                String category = rs.getString("category");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                inventoryArea.append("Name: " + name + ", Category: " + category + ", Quantity: " + quantity + ", Price: " + price + "\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching inventory: " + e.getMessage());
        }
    }

    // Method to add Supplier
    private void addSupplier() {
        String name = supplierNameField.getText();
        String type = supplierTypeField.getText();
        String contact = contactField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        if (name.isEmpty() || type.isEmpty() || contact.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!");
            return;
        }

        String insertQuery = "INSERT INTO suppliers (name, type, contact_person, phone, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(insertQuery)) {
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setString(3, contact);
            stmt.setString(4, phone);
            stmt.setString(5, email);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Supplier added successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding supplier: " + e.getMessage());
        }
    }

    // Method to show Suppliers
    private void showSuppliers() {
        String selectQuery = "SELECT * FROM suppliers";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(selectQuery);
             ResultSet rs = stmt.executeQuery()) {

            StringBuilder suppliersList = new StringBuilder();
            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                suppliersList.append("Name: ").append(name).append(", Type: ").append(type).append("\n");
            }

            JOptionPane.showMessageDialog(this, suppliersList.toString());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching suppliers: " + e.getMessage());
        }
    }

    // Method to add User
    private void addUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String role = roleField.getText();

        if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!");
            return;
        }

        String insertQuery = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(insertQuery)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "User added successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding user: " + e.getMessage());
        }
    }

    // Method to show Users
    private void showUsers() {
        String selectQuery = "SELECT * FROM users";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(selectQuery);
             ResultSet rs = stmt.executeQuery()) {

            StringBuilder usersList = new StringBuilder();
            while (rs.next()) {
                String username = rs.getString("username");
                String role = rs.getString("role");
                usersList.append("Username: ").append(username).append(", Role: ").append(role).append("\n");
            }

            JOptionPane.showMessageDialog(this, usersList.toString());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching users: " + e.getMessage());
        }
    }

    // Method to create Order
    private void createOrder() {
        String productIdStr = orderProductField.getText();
        String quantityStr = orderQuantityField.getText();

        if (productIdStr.isEmpty() || quantityStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!");
            return;
        }

        int productId, quantity;
        try {
            productId = Integer.parseInt(productIdStr);
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for product ID and quantity!");
            return;
        }

        // Query to get product details
        String selectProductQuery = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(selectProductQuery)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int availableQuantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                if (availableQuantity < quantity) {
                    JOptionPane.showMessageDialog(this, "Insufficient stock!");
                    return;
                }

                // Calculate total price and update stock
                double totalPrice = price * quantity;
                totalPriceField.setText(String.format("%.2f", totalPrice));

                // Update product quantity
                String updateProductQuery = "UPDATE products SET quantity = quantity - ? WHERE id = ?";
                try (PreparedStatement updateStmt = DatabaseConnection.getInstance().getConnection().prepareStatement(updateProductQuery)) {
                    updateStmt.setInt(1, quantity);
                    updateStmt.setInt(2, productId);
                    updateStmt.executeUpdate();
                }

                // Add order
                String insertOrderQuery = "INSERT INTO orders (product_id, quantity, total_price) VALUES (?, ?, ?)";
                try (PreparedStatement insertStmt = DatabaseConnection.getInstance().getConnection().prepareStatement(insertOrderQuery)) {
                    insertStmt.setInt(1, productId);
                    insertStmt.setInt(2, quantity);
                    insertStmt.setDouble(3, totalPrice);
                    insertStmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Order created successfully!");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Product not found!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error creating order: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InventoryManagementApp().setVisible(true);
        });
    }
}*/

