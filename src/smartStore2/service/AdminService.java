package smartStore2.service;

import smartstore.dal.dao.AdminDAO;

public class AdminService {
    AdminDAO dao = new AdminDAO();

    public void login(String user, String pass) throws Exception {
        if (dao.login(user, pass))
            System.out.println("Login Successful!");
        else
            System.out.println("Invalid Username or Password!");
    }
}
