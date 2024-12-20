package view;



import javax.swing.*;
import java.awt.*;
import decorator.simplebutton;
import decorator.LoggingButtonDecorator;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import observer.Observer;
import database.DatabaseConnection;

// Command Pattern to handle button actions
interface Command {
    void execute();
}

class AddProductCommand implements Command {
    private InventoryManagementApp app;

    public AddProductCommand(InventoryManagementApp app) {
        this.app = app;
    }

    @Override
    public void execute() {
        app.addProduct();
    }
}

class ShowInventoryCommand implements Command {
    private InventoryManagementApp app;

    public ShowInventoryCommand(InventoryManagementApp app) {
        this.app = app;
    }

    @Override
    public void execute() {
        app.showInventory();
    }
}

class AddSupplierCommand implements Command {
    private InventoryManagementApp app;

    public AddSupplierCommand(InventoryManagementApp app) {
        this.app = app;
    }

    @Override
    public void execute() {
        app.addSupplier();
    }
}

class ShowSuppliersCommand implements Command {
    private InventoryManagementApp app;

    public ShowSuppliersCommand(InventoryManagementApp app) {
        this.app = app;
    }

    @Override
    public void execute() {
        app.showSuppliers();
    }
}

class AddUserCommand implements Command {
    private InventoryManagementApp app;

    public AddUserCommand(InventoryManagementApp app) {
        this.app = app;
    }

    @Override
    public void execute() {
        app.addUser();
    }
}

class ShowUsersCommand implements Command {
    private InventoryManagementApp app;

    public ShowUsersCommand(InventoryManagementApp app) {
        this.app = app;
    }

    @Override
    public void execute() {
        app.showUsers();
    }
}

class CreateOrderCommand implements Command {
    private InventoryManagementApp app;

    public CreateOrderCommand(InventoryManagementApp app) {
        this.app = app;
    }

    @Override
    public void execute() {
        app.createOrder();
    }
}

// Strategy Pattern to handle various table models
interface TableModelStrategy {
    DefaultTableModel createTableModel();
}

class ProductTableModelStrategy implements TableModelStrategy {
    @Override
    public DefaultTableModel createTableModel() {
        return new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Name", "Category", "Quantity", "Price"});
    }
}

class SupplierTableModelStrategy implements TableModelStrategy {
    @Override
    public DefaultTableModel createTableModel() {
        return new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Name", "Type", "Contact", "Phone", "Email"});
    }
}

class UserTableModelStrategy implements TableModelStrategy {
    @Override
    public DefaultTableModel createTableModel() {
        return new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Username", "Role"});
    }
}

class OrderTableModelStrategy implements TableModelStrategy {
    @Override
    public DefaultTableModel createTableModel() {
        return new DefaultTableModel(new Object[][]{}, new String[]{"Order ID", "Product ID", "Quantity", "Total Price"});
    }
}

// InventoryManagementApp GUI with Observer Pattern for real-time updates
public class InventoryManagementApp extends JFrame {

    private JTextField nameField, quantityField, priceField, categoryField;
    private JTextArea inventoryArea;
    private JTextField supplierNameField, supplierTypeField, contactField, phoneField, emailField;
    private JTextField orderProductField, orderQuantityField, totalPriceField;
    private JTextField usernameField, roleField;
    private JPasswordField passwordField;
    private JTable productTable, supplierTable, orderTable;

    // Observer Pattern components

//private List<Observer> observers = new ArrayList<>();


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
        addProductButton.addActionListener(e -> new AddProductCommand(this).execute());
        showInventoryButton.addActionListener(e -> new ShowInventoryCommand(this).execute());
        addSupplierButton.addActionListener(e -> new AddSupplierCommand(this).execute());
        showSuppliersButton.addActionListener(e -> new ShowSuppliersCommand(this).execute());
        addUserButton.addActionListener(e -> new AddUserCommand(this).execute());
        showUsersButton.addActionListener(e -> new ShowUsersCommand(this).execute());
        createOrderButton.addActionListener(e -> new CreateOrderCommand(this).execute());
    }

    // Method to add Product
    void addProduct() {
                  // Create a simple button
        simplebutton simplebutton = new simplebutton("Add Product");

        // Decorate the button with logging behavior
        LoggingButtonDecorator loggingButton = new LoggingButtonDecorator(simplebutton);

        // Use the decorated button in your GUI
        loggingButton.render();
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
    void showInventory() {
                          // Create a simple button
        simplebutton simplebutton = new simplebutton("Show Inventory");

        // Decorate the button with logging behavior
        LoggingButtonDecorator loggingButton = new LoggingButtonDecorator(simplebutton);

        // Use the decorated button in your GUI
        loggingButton.render();
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
    void addSupplier() {
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
    void showSuppliers() {
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
    void addUser() {
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
    void showUsers() {
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

    // Method to create an Order
    void createOrder() {
        String productIdStr = orderProductField.getText();
        String quantityStr = orderQuantityField.getText();

        if (productIdStr.isEmpty() || quantityStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!");
            return;
        }

        int productId;
        int quantity;
        try {
            productId = Integer.parseInt(productIdStr);
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Product ID and Quantity!");
            return;
        }

        String selectQuery = "SELECT price FROM products WHERE id = ?";
        try (PreparedStatement stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(selectQuery)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double price = rs.getDouble("price");
                double totalPrice = price * quantity;
                totalPriceField.setText(String.valueOf(totalPrice));

                String insertQuery = "INSERT INTO orders (product_id, quantity, total_price) VALUES (?, ?, ?)";
                try (PreparedStatement insertStmt = DatabaseConnection.getInstance().getConnection().prepareStatement(insertQuery)) {
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
}



