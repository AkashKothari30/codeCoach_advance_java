package smartStore2.ui;

import smartStore2.model.Customer;
import smartStore2.service.CoustomerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;

public class CustomerPanel extends JPanel {
    private final CoustomerService service = new CoustomerService();

    private final JTextField nameField = new JTextField(20);
    private final JTextField emailField = new JTextField(20);
    private final JTextField phoneField = new JTextField(15);
    private final JTextArea listArea = new JTextArea(12, 50);

    public CustomerPanel() {
        setLayout(new BorderLayout(8, 8));
        buildForm();
        buildListArea();
        refreshList();
    }

    private void buildForm() {
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; form.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; form.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1; form.add(phoneField, gbc);

        JButton addBtn = new JButton("Add Customer");
        addBtn.addActionListener(this::onAdd);
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshList());

        JPanel buttons = new JPanel();
        buttons.add(addBtn);
        buttons.add(refreshBtn);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        form.add(buttons, gbc);

        add(form, BorderLayout.NORTH);
    }

    private void buildListArea() {
        listArea.setEditable(false);
        listArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane sp = new JScrollPane(listArea);
        add(sp, BorderLayout.CENTER);
    }

    private void onAdd(ActionEvent e) {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        try {
            service.addCustomer(name, email, phone);
            JOptionPane.showMessageDialog(this, "Customer added.");
            clearForm();
            refreshList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        nameField.requestFocusInWindow();
    }

    private void refreshList() {
        List<Customer> all = service.getAllCustomers();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-4s | %-20s | %-25s | %-15s%n", "ID", "Name", "Email", "Phone"));
        sb.append(String.join("", Collections.nCopies(80, "-"))).append("\n");
        for (Customer c : all) {
            sb.append(String.format("%-4d | %-20s | %-25s | %-15s%n",
                    c.getId(), safe(c.getName()), safe(c.getEmail()), safe(c.getPhone())));
        }
        listArea.setText(sb.toString());
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }

    // helper to show as standalone window for quick testing
    public static void showInFrame() {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Customers");
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.getContentPane().add(new CustomerPanel());
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
