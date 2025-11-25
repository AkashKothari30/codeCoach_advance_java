package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MoviesDemo{
    public static void main(String[] args) {
        System.out.println("Hello,JDBC,Movies");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            return;
        }
        String url = "jdbc:mysql://localhost:3306/movies";
        String user = "root";
        String password = "root";
        try(Connection conn = DriverManager.getConnection(url,user,password)){
            System.out.println("Connected to the database successfully!");
            try(Statement stmt = conn.createStatement()){
                System.out.println("Created statement..");
                String sql = "SELECT * FROM list_of_movies";
                System.out.println("Executing query...");
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("Query executed successfully");
                while (rs.next()){
                    String name = rs.getString("name");
                    int year = rs.getInt("year");
                    String revenue = rs.getString("revenue");
                    float review = rs.getFloat("review");
                    int season = rs.getInt("season");
                    System.out.println("name: " + name + ", year: " + year + ", revenue: " + revenue + ", review: " + review + ", season: " + season);


                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}