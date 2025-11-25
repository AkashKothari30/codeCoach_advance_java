package smartstore.dal;

import smartstore.dal.model.OrderItem;
import smartstore.dal.model.Product;
import smartstore.dal.service.*;

import java.util.*;

public class MainMenu {

    static Scanner sc = new Scanner(System.in);

    static AdminService adminService = new AdminService();
    static ProductService productService = new ProductService();
    static CustomerService customerService = new CustomerService();
    static OrderService orderService = new OrderService();

    public static void main(String[] args) {

        while (true) {
            System.out.println("SMART STORE MENU");
            System.out.println("1. Admin Login");
            System.out.println("2. Add Product");
            System.out.println("3. View Products");
            System.out.println("4. Register Customer");
            System.out.println("5. Create Order");
            System.out.println("6. View All Orders");
            System.out.println("7. Exit");
            System.out.print("Choice: ");
            
            int ch = sc.nextInt();
            sc.nextLine();

            try {
                switch (ch) {

                    case 1 -> adminLogin();

                    case 2 -> addProduct();

                    case 3 -> productService.viewProducts();

                    case 4 -> addCustomer();

                    case 5 -> createOrder();

                    case 6 -> orderService.viewAllOrders();

                    case 7 -> System.exit(0);

                    default -> System.out.println("Invalid Choice!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
 
    static void adminLogin() throws Exception {
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();
        adminService.login(u, p);
    }

    static void addProduct() throws Exception {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Price: ");
        double price = sc.nextDouble();
        System.out.print("Stock: ");
        int stock = sc.nextInt();

        productService.addProduct(name, price, stock);
    }

    static void addCustomer() throws Exception {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();

        customerService.addCustomer(name, email, phone);
    }

    static void createOrder() throws Exception {
        System.out.print("Customer ID: ");
        int cid = sc.nextInt();
        sc.nextLine();

        int orderId = orderService.createOrder(cid);
        System.out.println("Order Created! ID = " + orderId);

        ArrayList<OrderItem> cart = new ArrayList<>();

        while (true) {
            productService.viewProducts();

            System.out.print("Enter Product ID: ");
            int pid = sc.nextInt();

            System.out.print("Quantity: ");
            int qty = sc.nextInt();
            sc.nextLine();

            Product p = productService.getProduct(pid);
            if (p == null || qty > p.getStock()) {
                System.out.println("Invalid product or low stock!");
                continue;
            }

            double total = p.getPrice() * qty;

            cart.add(new OrderItem(pid, p.getName(), qty, total));

            productService.reduceStock(pid, qty);

            System.out.print("Add more items? yes/no: ");
            String more = sc.nextLine();
            if (!more.equalsIgnoreCase("yes")) break;
        }

        for (OrderItem it : cart)
            orderService.addItem(orderId, it);

        System.out.println("--- ORDER SUMMARY ---");
        double gtotal = 0;
        for (OrderItem it : cart) {
            System.out.println(it);
            gtotal += it.totalPrice;
        }
        System.out.println("GRAND TOTAL = " + gtotal);
    }
}
