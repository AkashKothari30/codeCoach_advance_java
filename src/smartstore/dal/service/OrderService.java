package smartstore.dal.service;

import smartstore.dal.dao.OrderDAO;
import smartstore.dal.model.OrderItem;

public class OrderService {

    OrderDAO dao = new OrderDAO();

    public int createOrder(int customerId) throws Exception {
        return dao.createOrder(customerId);
    }

    public void addItem(int orderId, OrderItem item) throws Exception {
        dao.addOrderItem(orderId, item);
    }

    public void viewAllOrders() throws Exception {
        dao.viewAllOrders();
    }
}
