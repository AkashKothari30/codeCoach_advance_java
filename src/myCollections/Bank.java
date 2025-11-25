package myCollections;

import java.util.ArrayList;

public class Bank {
    private String bankName;
    private ArrayList<Customers> customers = new ArrayList<>();

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    public Customers addCustomers(Customers c) {
        customers.add(c);
        return c;
    }

    public void showAllCustomers() {
        for (Customers c : customers) {
            c.showDetails();
            
        }
    }
}

