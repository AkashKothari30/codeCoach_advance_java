package smartStore2.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import smartStore2.model.OrderItem;

/*
 Replacement simple in-memory OrderService so UI can work without external DAO.
*/
public class OrderService {
    private final Map<Integer, List<OrderItem>> orders = Collections.synchronizedMap(new HashMap<>());
    private int nextOrderId = 1;

    public OrderService() {}

    public synchronized int createOrder(int customerId) {
        int orderId = nextOrderId++;
        orders.put(orderId, new ArrayList<>());
        System.out.println("Created order " + orderId + " for customer " + customerId);
        return orderId;
    }

    public synchronized void addItem(int orderId, OrderItem item) throws Exception {
        List<OrderItem> list = orders.get(orderId);
        if (list == null) {
            throw new Exception("Order not found: " + orderId);
        }
        list.add(item);
        System.out.println("Added item to order " + orderId + ": " + item);
    }

    public synchronized List<OrderItem> getItems(int orderId) {
        List<OrderItem> list = orders.get(orderId);
        if (list == null) return Collections.emptyList();
        return new ArrayList<>(list);
    }

    public synchronized Map<Integer, List<OrderItem>> getAllOrders() {
        // return shallow copy
        Map<Integer, List<OrderItem>> copy = new HashMap<>();
        for (Map.Entry<Integer, List<OrderItem>> e : orders.entrySet()) {
            copy.put(e.getKey(), new ArrayList<>(e.getValue()));
        }
        return copy;
    }
}
