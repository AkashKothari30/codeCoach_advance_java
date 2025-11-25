package myCollections;
import java.util.ArrayList;

public class Customer {
    private String name;
    private int qty;

    private ArrayList<Address> listofAddress = new ArrayList<>();

    public Customer(String name , int qty){
        this.name = name;
        this.qty = qty;

    }
    public void addAddress(Address address){
        listofAddress.add(address);

    }


    public void listAddresses(){
        for (int i = 0; i < listofAddress.size(); i++) {
            System.out.println((i+1) + ". " + listofAddress.get(i).getFullAddress());
        }
    }

    public void showdetails(){
        System.out.println("Customer Name: " + name);
        System.out.println("Quantity: " + qty);
        System.out.println("Addresses:");
        listAddresses();
    }
    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }
    public String getEmail() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEmail'");
    }

}
