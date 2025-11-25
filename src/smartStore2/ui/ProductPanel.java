package smartStore2.ui;

import smartStore2.service.ProductService;
import smartstore.dal.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductPanel extends JPanel {

    private final ProductService productService = new ProductService();
    private final DefaultTableModel tableModel;
    private final JTable table;

    public ProductPanel() {
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        String[] cols = {"ID", "Name", "Price", "Stock"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        JButton btnRefresh = new JButton("Refresh");
        JButton btnAdd = new JButton("Add Product");
        JButton btnReduce = new JButton("Reduce Stock");

        btnRefresh.addActionListener(e -> refreshTable());
        btnAdd.addActionListener(e -> onAddProduct());
        btnReduce.addActionListener(e -> onReduceStock());

        buttons.add(btnAdd);
        buttons.add(btnReduce);
        buttons.add(btnRefresh);

        add(buttons, BorderLayout.SOUTH);

        // load initially
        refreshTable();
    }

    private void refreshTable() {
        SwingUtilities.invokeLater(() -> {
            try {
                tableModel.setRowCount(0);
                List<Product> list = productService.getAllProducts();
                for (Product p : list) {
                    tableModel.addRow(new Object[]{p.getId(), p.getName(), p.getPrice(), p.getStock()});
                }
            } catch (Exception ex) {
                showError(ex);
            }
        });
    }

    private void onAddProduct() {
        try {
            String name = JOptionPane.showInputDialog(this, "Product Name:");
            if (name == null || name.isBlank()) return;

            String priceS = JOptionPane.showInputDialog(this, "Price:");
            if (priceS == null) return;
            double price = Double.parseDouble(priceS);

            String stockS = JOptionPane.showInputDialog(this, "Stock:");
            if (stockS == null) return;
            int stock = Integer.parseInt(stockS);

            productService.addProduct(name, price, stock);
            JOptionPane.showMessageDialog(this, "Product added.");
            refreshTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void onReduceStock() {
        int sel = table.getSelectedRow();
        if (sel < 0) {
            JOptionPane.showMessageDialog(this, "Select a product first.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int id = (int) tableModel.getValueAt(sel, 0);
        try {
            String qtyS = JOptionPane.showInputDialog(this, "Quantity to reduce:");
            if (qtyS == null) return;
            int qty = Integer.parseInt(qtyS);
            productService.reduceStock(id, qty);
            JOptionPane.showMessageDialog(this, "Stock updated.");
            refreshTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void showError(Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
