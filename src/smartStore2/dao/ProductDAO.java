package smartStore2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import smartStore2.model.Product;
import smartstore.util.DBConnection;

public class ProductDAO {
    Connection con = DBConnection.getConnection();

    public int addProduct(Product p) throws Exception {
        String sql = "INSERT INTO products(name, price, stock) VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, p.getName());
        ps.setDouble(2, p.getPrice());
        ps.setInt(3, p.getStock());
        return ps.executeUpdate();
    }

    public ArrayList<Product> getAllProducts() throws Exception {
        ArrayList<Product> list = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM products");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new Product(
                rs.getInt("product_id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("stock")
            ));
        }
        return list;
    }

    public Product getProductById(int id) throws Exception {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE product_id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Product(
                rs.getInt("product_id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("stock")
            );
        }
        return null;
    }

    public void updateStock(int productId, int qty) throws Exception {
        PreparedStatement ps = con.prepareStatement(
            "UPDATE products SET stock = stock - ? WHERE product_id=?");
        ps.setInt(1, qty);
        ps.setInt(2, productId);
        ps.executeUpdate();
    }
}

