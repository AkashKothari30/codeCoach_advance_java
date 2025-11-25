package smartstore.dal.service;

import smartstore.dal.dao.CustomerDAO;
import smartstore.dal.model.Customer;

public class CustomerService {
    CustomerDAO dao = new CustomerDAO();

    public void addCustomer(String name, String email, String phone) throws Exception {
        dao.addCustomer(new Customer(name, email, phone));
        System.out.println("Customer Registered Successfully!");
    }
}
