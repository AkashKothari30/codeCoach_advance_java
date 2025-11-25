package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Smart_Store_Management_System {
    static Scanner sc = new Scanner(System.in);
    static Connection con;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String url = "jdbc:mysql://localhost:3306/smart_store_management_system";
        String user = "root";
        String password = "root";

        con = DriverManager.getConnection(url, user, password);

        while (true) {
            System.out.println("SMART STORE MENU ...");
            System.out.println("1. Admin Login");
            System.out.println("2. Add Product");
            System.out.println("3. View Products");
            System.out.println("4. Register Customer");
            System.out.println("5. Create Order");
            System.out.println("6. View All Orders");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    viewProducts();
                    break;
                case 4:
                    addCustomer();
                    break;
                case 5:
                    createOrder();
                    break;
                case 6:
                    viewAllOrders();
                    break;
                case 7:
                    System.out.println("Exiting... Bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void adminLogin() {
        try {
            System.out.print("Enter Username: ");
            String user = sc.nextLine();
            System.out.print("Enter Password: ");
            String pass = sc.nextLine();

            String sql = "SELECT * FROM admin WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login Successful! Welcome, " + user);
            } else {
                System.out.println("Invalid Username or Password!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addProduct() {
        try {
            System.out.println("--- Add Product ---");
            System.out.print("Enter Product Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Product Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter Product Quantity: ");
            int qty = sc.nextInt();

            String sql = "INSERT INTO products (name, price, stock) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);

            int rows = ps.executeUpdate();
            System.out.println(rows + " product(s) added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void viewProducts() {
        try {
            System.out.println("--- View Products ---");
            String sql = "SELECT * FROM products";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                    "ID: " + rs.getInt("product_id") +
                    ", Name: " + rs.getString("name") +
                    ", Price: " + rs.getDouble("price") +
                    ", Stock: " + rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addCustomer() {
        try {
            System.out.println("--- Register Customer ---");
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();

            String sql = "INSERT INTO customers (name, email, phone) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            int rows = ps.executeUpdate();
            System.out.println(rows + " customer(s) registered.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   static void createOrder() {
    try {
        System.out.print("Enter Customer ID: ");
        int cid = sc.nextInt();
        sc.nextLine();

        
        PreparedStatement ps1 = con.prepareStatement(
            "INSERT INTO orders(customer_id) VALUES(?)",
            Statement.RETURN_GENERATED_KEYS);

        ps1.setInt(1, cid);
        ps1.executeUpdate();

        ResultSet rs = ps1.getGeneratedKeys();
        int orderId = 0;
        if (rs.next()) orderId = rs.getInt(1);

        System.out.println("Order Created. Order ID = " + orderId);

    
        ArrayList<OrderItem> orderItemsList = new ArrayList<>();

        while (true) {

            
            ArrayList<product> products = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products");
            ResultSet rs2 = ps.executeQuery();

            System.out.println("--- Available Products ---");
            while (rs2.next()) {
                product p = new product(
                        rs2.getInt("product_id"),
                        rs2.getString("name"),
                        rs2.getDouble("price"),
                        rs2.getInt("stock")
                );
                products.add(p);
                System.out.println(p);
            }

            
            System.out.print("Enter Product ID: ");
            int pid = sc.nextInt();

            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            
            PreparedStatement ps2 = con.prepareStatement(
                    "SELECT * FROM products WHERE product_id=?");
            ps2.setInt(1, pid);
            ResultSet prs = ps2.executeQuery();

            if (prs.next()) {

                double price = prs.getDouble("price");
                int stock = prs.getInt("stock");
                String pname = prs.getString("name");

                if (qty > stock) {
                    System.out.println("Not enough stock!");
                    continue;
                }

                double total = price * qty;

                
                orderItemsList.add(new OrderItem(pid, pname, qty, total));
                System.out.println("Product added to cart!");

    
                PreparedStatement ps4 = con.prepareStatement(
                    "UPDATE products SET stock = stock - ? WHERE product_id = ?");
                ps4.setInt(1, qty);
                ps4.setInt(2, pid);
                ps4.executeUpdate();

            } else {
                System.out.println("Invalid Product ID!");
            }

            sc.nextLine();
            System.out.print("Do you want to add more items? (yes/no): ");
            String more = sc.nextLine();

            if (!more.equalsIgnoreCase("yes")) {
                System.out.println("Thanks for visiting!");
                break;
            }
        }
        for (OrderItem item : orderItemsList) {
            PreparedStatement ps3 = con.prepareStatement(
                    "INSERT INTO orders_item(orders_id, product_id, quntity, price) VALUES(?,?,?,?)");

            ps3.setInt(1, orderId);
            ps3.setInt(2, item.productId);
            ps3.setInt(3, item.qty);
            ps3.setDouble(4, item.totalPrice);
            ps3.executeUpdate();
        }


        System.out.println("--- ORDER SUMMARY ---");
        double grandTotal = 0;

        for (OrderItem item : orderItemsList) {
            System.out.println(item);
            grandTotal += item.totalPrice;
        }

        System.out.println("GRAND TOTAL = " + grandTotal);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    static void viewAllOrders() {
        try {
            System.out.println("--- All Orders ---");
            String sql = "SELECT * FROM orders";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Order ID: " + rs.getInt("orders_id") +
                        ", Customer ID: " + rs.getInt("customer_id") +
                        ", Date: " + rs.getTimestamp("orderdate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
