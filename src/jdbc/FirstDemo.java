package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstDemo {
    public static void main(String[] args) {
        System.out.println("Hello, JDBC!");
        // load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return; // exit if driver not found
        }

        // create connection
        String url = "jdbc:mysql://localhost:3306/library"; // replace with your database URL
        String user = "root"; // replace with your database username
        String password = "root"; // replace with your database password
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the database successfully!");
            // create statement
            try (Statement stmt = conn.createStatement()) {
                System.out.println("Created statement...");
                // execute SQL statement
                String sql = "SELECT * FROM book"; // replace with your SQL query
                System.out.println("Executing query...");
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("Query executed successfully!");
                // process the result set
                while (rs.next()) {
                    int id = rs.getInt("id"); // replace with your column name
            
                    String name  = rs.getString("name"); // replace with your column name
                    String author = rs.getString("author"); // replace with your column name
                    int price = rs.getInt("price"); // replace with your column name
                    System.out.println("id: " + id + ", name: " + name + ", author: " + author + ", price: " + price);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Here you can add code to connect to a database and perform operations
    }
}