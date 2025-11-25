package smartstore.dal.service;

import smartstore.dal.dao.ProductDAO;
import smartstore.dal.model.Product;

import java.util.ArrayList;

public class ProductService {

    ProductDAO dao = new ProductDAO();

    public void addProduct(String name, double price, int stock) throws Exception {
        dao.addProduct(new Product(name, price, stock));
        System.out.println("Product Added Successfully!");
    }

    public void viewProducts() throws Exception {
        ArrayList<Product> list = dao.getAllProducts();
        list.forEach(System.out::println);
    }

    public Product getProduct(int id) throws Exception {
        return dao.getProductById(id);
    }

    public void reduceStock(int id, int qty) throws Exception {
        dao.updateStock(id, qty);
    }
}
