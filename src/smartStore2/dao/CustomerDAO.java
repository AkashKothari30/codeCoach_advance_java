package smartStore2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import smartstore.util.DBConnection;

public class CustomerDAO {
     Connection con = DBConnection.getConnection();

    public int addCustomer(smartstore.dal.model.Customer c) throws Exception {
        String sql = "INSERT INTO customers(name, email, phone) VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, c.getName());
        ps.setString(2, c.getEmail());
        ps.setString(3, c.getName());
        return ps.executeUpdate();
    }
}
