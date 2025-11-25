package myCollections;

    public class BankDemo {
    public static void main(String[] args) {
        Bank bank = new Bank("State Bank Of India");

        Customers customer1 = new Customers("Akash", "12345", 50000);
        customer1.addAddress(new Address("India", "Uttarakhand", "248001", "Rajpur Road"));

        Customers customer2 = new Customers("Yash", "88888", 20000);
        customer2.addAddress(new Address("India", "Delhi", "110001", "Karol Bagh"));

        Customers customer3 = new Customers("vishal", "639864", 100000);
        customer3.addAddress(new Address("India", "noida", "123456", "noida"));

        Customers customer4 = new Customers("vansh", "23432", 1500);
        customer4.addAddress(new Address("India", "Dehradun", "248121","Banjarawala"));

        bank.addCustomers(customer1);
        bank.addCustomers(customer2);
        bank.addCustomers(customer3);
        bank.addCustomers(customer4);

        bank.showAllCustomers();
    }
}


