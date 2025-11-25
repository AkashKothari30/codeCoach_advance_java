package myCollections;

 import java.util.ArrayList;

public class Customers {
    private String name;
    private String accountNumber;
    private double balance;
    private ArrayList<Address> addresses = new ArrayList<>();

    public Customers(String name, String accountNumber, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public void listAddresses() {
        for (int i = 0; i < addresses.size(); i++) {
            System.out.println((i+1) + ". " + addresses.get(i).getFullAddress());
        }
    }

    public void showDetails() {
        System.out.println("Name: " + name);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
        System.out.println("Addresses:");
        listAddresses();
    }

}
 
    

