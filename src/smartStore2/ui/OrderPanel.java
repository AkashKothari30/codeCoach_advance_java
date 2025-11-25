package smartStore2.ui;

import smartStore2.model.OrderItem;
import smartStore2.service.OrderService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

public class OrderPanel extends JPanel {
    private final OrderService service = new OrderService();

    private final JTextField customerIdField = new JTextField(6);
    private final JTextField orderIdField = new JTextField(6);
    private final JTextField productIdField = new JTextField(6);
    private final JTextField productNameField = new JTextField(15);
    private final JTextField quantityField = new JTextField(4);
    private final JTextField unitPriceField = new JTextField(6);

    private final JTextArea listArea = new JTextArea(16, 60);

    public OrderPanel() {
        setLayout(new BorderLayout(8, 8));
        buildForm();
        buildListArea();
        refreshList();
    }

    private void buildForm() {
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4,4);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Customer ID:"), gbc);
        gbc.gridx = 1; form.add(customerIdField, gbc);
        gbc.gridx = 2; form.add(new JButton(new AbstractAction("Create Order") {
            @Override public void actionPerformed(ActionEvent e) { onCreateOrder(); }
        }), gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Order ID:"), gbc);
        gbc.gridx = 1; form.add(orderIdField, gbc);
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshList());
        gbc.gridx = 2; form.add(refreshBtn, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Product ID:"), gbc);
        gbc.gridx = 1; form.add(productIdField, gbc);
        gbc.gridx = 2; form.add(new JLabel("Name:"), gbc);
        gbc.gridx = 3; form.add(productNameField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Qty:"), gbc);
        gbc.gridx = 1; form.add(quantityField, gbc);
        gbc.gridx = 2; form.add(new JLabel("Unit Price:"), gbc);
        gbc.gridx = 3; form.add(unitPriceField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 4;
        JPanel buttons = new JPanel();
        JButton addItemBtn = new JButton("Add Item");
        addItemBtn.addActionListener(this::onAddItem);
        JButton viewBtn = new JButton("View Orders");
        viewBtn.addActionListener(e -> refreshList());
        buttons.add(addItemBtn);
        buttons.add(viewBtn);
        form.add(buttons, gbc);

        add(form, BorderLayout.NORTH);
    }

    private void buildListArea() {
        listArea.setEditable(false);
        listArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        add(new JScrollPane(listArea), BorderLayout.CENTER);
    }

    private void onCreateOrder() {
        try {
            int customerId = Integer.parseInt(customerIdField.getText().trim());
            int orderId = service.createOrder(customerId);
            orderIdField.setText(String.valueOf(orderId));
            JOptionPane.showMessageDialog(this, "Order created: " + orderId);
            refreshList();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid customer id", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onAddItem(ActionEvent e) {
        try {
            int orderId = Integer.parseInt(orderIdField.getText().trim());
            int productId = Integer.parseInt(productIdField.getText().trim());
            String productName = productNameField.getText().trim();
            int qty = Integer.parseInt(quantityField.getText().trim());
            double unitPrice = Double.parseDouble(unitPriceField.getText().trim());
            double total = qty * unitPrice;

            OrderItem item = new OrderItem(productId, productName, qty, total);
            service.addItem(orderId, item);

            JOptionPane.showMessageDialog(this, "Item added to order " + orderId);
            clearItemFields();
            refreshList();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Numeric fields required", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearItemFields() {
        productIdField.setText("");
        productNameField.setText("");
        quantityField.setText("");
        unitPriceField.setText("");
        productIdField.requestFocusInWindow();
    }

    private void refreshList() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-6s | %-8s | %s%n", "Order", "ProdID", "Product / Qty / Total"));
        sb.append("".join("", java.util.Collections.nCopies(90, "-"))).append("\n");

        Map<Integer, List<OrderItem>> all = service.getAllOrders();
        if (all.isEmpty()) {
            sb.append("No orders\n");
        } else {
            for (Map.Entry<Integer, List<OrderItem>> e : all.entrySet()) {
                int oid = e.getKey();
                sb.append("Order ").append(oid).append(":\n");
                double orderTotal = 0.0;
                for (OrderItem it : e.getValue()) {
                    sb.append(String.format("   %-6d | %-6s | %-20s | Qty:%-3d | Total: %.2f%n",
                            it.productId, "", it.productName, it.quantity, it.totalPrice));
                    orderTotal += it.totalPrice;
                }
                sb.append(String.format("   Order Total: %.2f%n", orderTotal));
                sb.append("\n");
            }
        }
        listArea.setText(sb.toString());
    }

    // helper for standalone testing
    public static void showInFrame() {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Orders");
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.getContentPane().add(new OrderPanel());
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
