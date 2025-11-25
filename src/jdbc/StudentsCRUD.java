package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentsCRUD {
    public static void main(String[] args) {
        System.out.println("Hello, JDBC!");
        // Load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return; // Exit if driver not found
        }

        // Create connection
        String url = "jdbc:mysql://localhost:3306/students_db"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = ""; // Replace with your database password
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the database successfully!");
            // Create statement
            try (Statement stmt = conn.createStatement()) {
                System.out.println("Created statement...");

                // insert a new student
                // String insertSql = "INSERT INTO student (id, age, first, last) VALUES (3, 20,
                // 'John', 'Doe');";
                // int rowsInserted = stmt.executeUpdate(insertSql);
                // System.out.println(rowsInserted + " rows inserted");
                System.out.println("Select * students ...");
                String sql = "SELECT * FROM student"; // Replace with your SQL query
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("Query executed successfully!");
                while (rs.next()) {
                    int id = rs.getInt("id"); // replace with your column name
                    int age = rs.getInt("age"); // replace with your column name
                    String first = rs.getString("first"); // replace with your column name
                    String last = rs.getString("last"); // replace with your column name
                    System.out.println("ID: " + id + " age: " + age + ", Name: " + first + " " + last);
                }

                // update a student
                System.out.println("Updating student with ID 3 ...");
                String updateSql = "UPDATE student SET age = 22 WHERE id = 3;";
                int rowsUpdated = stmt.executeUpdate(updateSql);
                System.out.println(rowsUpdated + " rows updated");

                // select all students after update
                System.out.println("Select * students after update ...");
                rs = stmt.executeQuery(sql);

                System.out.println("Query executed successfully!");
                while (rs.next()) {
                    int id = rs.getInt("id"); // replace with your column name
                    int age = rs.getInt("age"); // replace with your column name
                    String first = rs.getString("first"); // replace with your column name
                    String last = rs.getString("last"); // replace with your column name
                    System.out.println("ID: " + id + " age: " + age + ", Name: " + first + " " + last);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Goodbye, JDBC!");
    }
}