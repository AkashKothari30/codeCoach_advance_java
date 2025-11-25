package smartstore.dal.dao;
import smartstore.dal.model.OrderItem;
import smartstore.util.DBConnection;
import java.sql.*;

public class OrderDAO {

    Connection con = DBConnection.getConnection();

    public int createOrder(int customerId) throws Exception {
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO orders(customer_id) VALUES(?)",
            Statement.RETURN_GENERATED_KEYS
        );
        ps.setInt(1, customerId);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public void addOrderItem(int orderId, OrderItem item) throws Exception {
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO orders_item(orders_id, product_id, quntity, price) VALUES(?,?,?,?)"
        );
        ps.setInt(1, orderId);
        ps.setInt(2, item.productId);
        ps.setInt(3, item.quantity);
        ps.setDouble(4, item.totalPrice);
        ps.executeUpdate();
    }

    public void viewAllOrders() throws Exception {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM orders");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(
                "Order ID: " + rs.getInt("orders_id") +
                ", Customer ID: " + rs.getInt("customer_id") +
                ", Date: " + rs.getTimestamp("orderdate")
            );
        }
    }
}
