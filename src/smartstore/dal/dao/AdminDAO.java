package smartstore.dal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import smartstore.util.DBConnection;

public class AdminDAO {
    Connection con = DBConnection.getConnection();

    public boolean login(String username, String password) throws Exception {
        String sql = "SELECT * FROM admin WHERE username=? AND password=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
}

