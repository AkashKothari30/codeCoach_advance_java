package myCollections;

public class TestStarWarList {
    public static void main(String[] args) {
        Customer customer = new Customer("Akash kothari", 03);

        Address address = new Address("India ", "Uttarakhand", "IND123", "Dehardun");
        Address address2 = new Address("India ", "Dehli", "IND1234", "Dehli");

        customer.addAddress(address);
        customer.addAddress(address2);

        customer.showdetails();

    }
}
