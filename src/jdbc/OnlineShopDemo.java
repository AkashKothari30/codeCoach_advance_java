package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OnlineShopDemo {
    public static void main(String[] args) {
        System.out.println("Hello, JDBC Online Shop");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        String url = "jdbc:mysql://localhost:3306/online_shop";
        String user = "root";
        String password = "root";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the database successfully!");

            try (Statement stmt = conn.createStatement()) {
                System.out.println("create statement.."); 
                System.out.println("Select * customers");
                String SQL = "SELECT * FROM customer";
                ResultSet rs1 = stmt.executeQuery(SQL);
                System.out.println("Query executed successfully!");
                while (rs1.next()) {
                    int id = rs1.getInt("id");
                    String name = rs1.getString("name");
                    String email = rs1.getString("email");
                    System.out.println("ID: " + id + "  Name: " + name + " Email: " + email);
                }
                System.out.println("updating new data.. ");
                String insertSQL = "INSERT INTO customer (name, email) VALUES ('Ram', 'Ram@123')";
                int rsbd = stmt.executeUpdate(insertSQL);
                System.out.println(rsbd + "inserted successfully!");
                System.out.println("Select * after updated");
                
                ResultSet rs2 = stmt.executeQuery(SQL);
                while (rs2.next()) {
                    int id = rs2.getInt("id");
                    String name = rs2.getString("name");
                    String email = rs2.getString("email");
                    System.out.println("ID: " + id + "  Name: " + name + " Email: " + email);
                }
                System.out.println("Updating customer with ID 1 ...");

                String updateSql = "UPDATE customer SET name = 'vikash' , email = 'vikash@4554' WHERE id = 1";
                int rowsUpdated = stmt.executeUpdate(updateSql);
                System.out.println(rowsUpdated + " rows updated");

                System.out.println("Select * customers after update ...");
                ResultSet rs3 = stmt.executeQuery(SQL);

                System.out.println("Query executed successfully!");
                while (rs3.next()) {
                    int id = rs3.getInt("id");
                    String name = rs3.getString("name");
                    String email = rs3.getString("email");
                    System.out.println("ID: " + id + "  Name: " + name + " Email: " + email);
                }
                
                /*System.out.println("Deleting customer with ID 2 ...");
                String deleteSql = "DELETE FROM customer WHERE id = 2";
                int rowsDeleted = stmt.executeUpdate(deleteSql);
                System.out.println(rowsDeleted + " rows deleted");
                System.out.println("Select * customers after deletion ...");
                ResultSet rs4 = stmt.executeQuery(SQL);
                System.out.println("Query executed successfully!");
                while (rs4.next()) {
                    int id = rs4.getInt("id");
                    String name = rs4.getString("name");
                    String email = rs4.getString("email");
                    System.out.println("ID: " + id + "  Name: " + name + " Email: " + email);
                }*/


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
