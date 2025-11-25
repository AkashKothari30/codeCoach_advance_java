package smartStore2.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import smartStore2.model.Customer;

public class CoustomerService {
    // simple in-memory store to keep things working without DAO
    private final List<Customer> store = Collections.synchronizedList(new ArrayList<>());
    private int nextId = 1;

    public CoustomerService() {}

    public synchronized void addCustomer(String name, String email, String phone) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Name is required");
        }
        Customer c = new Customer(nextId++, name.trim(), email == null ? "" : email.trim(),
                phone == null ? "" : phone.trim());
        store.add(c);
        System.out.println("Customer Registered Successfully!");
    }

    public List<Customer> getAllCustomers() {
        synchronized (store) {
            return new ArrayList<>(store);
        }
    }

    public Customer findById(int id) {
        synchronized (store) {
            return store.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        }
    }

    public boolean removeCustomer(int id) {
        synchronized (store) {
            return store.removeIf(c -> c.getId() == id);
        }
    }
}
