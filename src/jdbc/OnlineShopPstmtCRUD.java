package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class OnlineShopPstmtCRUD {
    public static void main(String[] args) {
        System.out.println("Hello, JDBC!");

        // Load the JDBC driver (optional for recent JDBC, kept for clarity)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
            return;
        }

        // Update these values for your environment if needed
        String url = "jdbc:mysql://localhost:3306/online_shop";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the database successfully!");

            // Example values to insert (change as needed)
            String productName = "biscuits";
            double productPrice = 2500.99;

            // INSERT example using PreparedStatement
            String insertSql = "INSERT INTO products (name, price) VALUES (?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setString(1, productName);
                insertStmt.setDouble(2, productPrice);
                int rowsInserted = insertStmt.executeUpdate();
                System.out.println(rowsInserted + " row(s) inserted.");

                try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long newId = generatedKeys.getLong(1);
                        System.out.println("Inserted product id: " + newId);
                    }
                }
            }

            // SELECT all products (include id)
            String selectSql = "SELECT id, name, price FROM products";
            try (PreparedStatement selectStmt = conn.prepareStatement(selectSql);
                 ResultSet rs = selectStmt.executeQuery()) {
                System.out.println("Current products:");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    System.out.println("ID: " + id + " Name: " + name + " Price: " + price);
                }
            }

            // UPDATE a product (example: change name and price for product with id = 2)
            String updateSql = "UPDATE products SET name = ?, price = ? WHERE id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                String newName = "biscuits";
                double newPrice = 3000.50;
                int idToUpdate = 2; // change as needed

                updateStmt.setString(1, newName);
                updateStmt.setDouble(2, newPrice);
                updateStmt.setInt(3, idToUpdate);

                int rowsUpdated = updateStmt.executeUpdate();
                System.out.println(rowsUpdated + " row(s) updated.");
            }

            // SELECT after update
            try (PreparedStatement selectStmt = conn.prepareStatement(selectSql);
                 ResultSet rs = selectStmt.executeQuery()) {
                System.out.println("Products after update:");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    System.out.println("ID: " + id + " Name: " + name + " Price: " + price);
                }
            }

            // delete prepared statement by user
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Enter product id to delete: ");
                if (scanner.hasNextInt()) {
                    int Delete = scanner.nextInt();
                    String deleteSql = "DELETE FROM products WHERE id = ?";
                    try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                        deleteStmt.setInt(1, Delete);
                        int rowsDeleted = deleteStmt.executeUpdate();
                        System.out.println(rowsDeleted + " row(s) deleted.");
                    }
                    try (PreparedStatement selectStmt = conn.prepareStatement(selectSql);
                         ResultSet rs = selectStmt.executeQuery()) {
                        System.out.println("Products after delete:");
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String name = rs.getString("name");
                            double price = rs.getDouble("price");
                            System.out.println("ID: " + id + " Name: " + name + " Price: " + price); 
                        }
                    }
                } else {
                    System.out.println("Invalid id input.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Goodbye, JDBC!");
    }
}