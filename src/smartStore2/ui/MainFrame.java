package smartStore2.ui;

import smartstore.dal.model.OrderItem;
import smartstore.dal.model.Product;
import smartstore.dal.service.AdminService;
import smartstore.dal.service.CustomerService;
import smartstore.dal.service.OrderService;
import smartstore.dal.service.ProductService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private final AdminService adminService = new AdminService();
    private final ProductService productService = new ProductService();
    private final CustomerService customerService = new CustomerService();
    private final OrderService orderService = new OrderService();

    public MainFrame() {
        setTitle("SMART STORE - Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 420);
        setLocationRelativeTo(null);

        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        p.setLayout(new GridLayout(0, 1, 8, 8));

        p.add(createButton("1. Admin Login", this::adminLogin));
        p.add(createButton("2. Add Product", this::addProduct));
        p.add(createButton("3. View Products", this::viewProducts));
        p.add(createButton("4. Register Customer", this::addCustomer));
        p.add(createButton("5. Create Order", this::createOrder));
        p.add(createButton("6. View All Orders", this::viewOrders));
        p.add(createButton("7. Exit", e -> System.exit(0)));

        add(p, BorderLayout.CENTER);
    }
    private JButton createButton(String text, ActionListener action) {
        JButton b = new JButton();
        b.addActionListener(action);
        b.setText(text);
        return b;
    }
    

    private void adminLogin(ActionEvent e) {
        try {
            String username = JOptionPane.showInputDialog(this, "Username:");
            if (username == null) return;

            JPasswordField pf = new JPasswordField();
            int ok = JOptionPane.showConfirmDialog(this, pf, "Password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (ok != JOptionPane.OK_OPTION) return;
            String password = new String(pf.getPassword());

            adminService.login(username, password);
            JOptionPane.showMessageDialog(this, "Login attempted (check console/service response).");
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void addProduct(ActionEvent e) {
        try {
            String name = JOptionPane.showInputDialog(this, "Product Name:");
            if (name == null) return;
            String priceS = JOptionPane.showInputDialog(this, "Price:");
            if (priceS == null) return;
            String stockS = JOptionPane.showInputDialog(this, "Stock:");
            if (stockS == null) return;

            double price = Double.parseDouble(priceS);
            int stock = Integer.parseInt(stockS);

            productService.addProduct(name, price, stock);
            JOptionPane.showMessageDialog(this, "Product added.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void viewProducts(ActionEvent e) {
        try {
            // show ProductPanel in a modal dialog so user can view/add/update products
            JDialog dlg = new JDialog(this, "Products", true);
            dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dlg.getContentPane().add(new ProductPanel());
            dlg.pack();
            dlg.setSize(700, 450);
            dlg.setLocationRelativeTo(this);
            dlg.setVisible(true);
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void addCustomer(ActionEvent e) {
        try {
            String name = JOptionPane.showInputDialog(this, "Name:");
            if (name == null) return;
            String email = JOptionPane.showInputDialog(this, "Email:");
            if (email == null) return;
            String phone = JOptionPane.showInputDialog(this, "Phone:");
            if (phone == null) return;

            customerService.addCustomer(name, email, phone);
            JOptionPane.showMessageDialog(this, "Customer registered.");
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void createOrder(ActionEvent e) {
        try {
            String cidS = JOptionPane.showInputDialog(this, "Customer ID:");
            if (cidS == null) return;
            int cid = Integer.parseInt(cidS);

            int orderId = orderService.createOrder(cid);
            JOptionPane.showMessageDialog(this, "Order Created! ID = " + orderId);

            ArrayList<OrderItem> cart = new ArrayList<>();

            while (true) {
                // attempt to show products first (best effort)
                viewProducts(null);

                String pidS = JOptionPane.showInputDialog(this, "Enter Product ID (or Cancel to finish):");
                if (pidS == null || pidS.isBlank()) break;
                int pid = Integer.parseInt(pidS);

                String qtyS = JOptionPane.showInputDialog(this, "Quantity:");
                if (qtyS == null) break;
                int qty = Integer.parseInt(qtyS);

                Product p = productService.getProduct(pid);
                if (p == null || qty > p.getStock()) {
                    JOptionPane.showMessageDialog(this, "Invalid product or low stock!", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                double total = p.getPrice() * qty;
                cart.add(new OrderItem(pid, p.getName(), qty, total));
                productService.reduceStock(pid, qty);

                int more = JOptionPane.showConfirmDialog(this, "Add more items?", "Continue", JOptionPane.YES_NO_OPTION);
                if (more != JOptionPane.YES_OPTION) break;
            }

            for (OrderItem it : cart) {
                orderService.addItem(orderId, it);
            }

            StringBuilder sb = new StringBuilder();
            double gtotal = 0;
            sb.append("--- ORDER SUMMARY ---\n");
            for (OrderItem it : cart) {
                sb.append(it).append("\n");
                gtotal += it.totalPrice;
            }
            sb.append("\nGRAND TOTAL = ").append(gtotal);
            showTextAreaDialog("Order Summary", sb.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void viewOrders(ActionEvent e) {
        try {
            // try to retrieve orders list via reflection
            Method getter = null;
            try {
                getter = orderService.getClass().getMethod("getAllOrders");
            } catch (NoSuchMethodException ignore) {
            }

            if (getter != null) {
                Object res = getter.invoke(orderService);
                StringBuilder sb = new StringBuilder();
                if (res instanceof List) {
                    for (Object o : (List<?>) res) sb.append(o).append("\n");
                } else sb.append(String.valueOf(res));
                showTextAreaDialog("All Orders", sb.toString());
            } else {
                // fallback to viewAllOrders() if exists
                try {
                    Method viewM = orderService.getClass().getMethod("viewAllOrders");
                    viewM.invoke(orderService);
                } catch (NoSuchMethodException ignore) {
                }
                JOptionPane.showMessageDialog(this, "Orders listed (console) or not available via API.");
            }
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void showTextAreaDialog(String title, String text) {
        JTextArea ta = new JTextArea(text);
        ta.setEditable(false);
        ta.setCaretPosition(0);
        JScrollPane sp = new JScrollPane(ta);
        sp.setPreferredSize(new Dimension(600, 400));
        JOptionPane.showMessageDialog(this, sp, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
