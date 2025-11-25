package smartStore2.service;

import java.util.ArrayList;
import java.util.List;

import smartstore.dal.dao.ProductDAO;
import smartstore.dal.model.Product;

public class ProductService {
    ProductDAO dao = new ProductDAO();

    public void addProduct(String name, double price, int stock) throws Exception {
        dao.addProduct(new Product(name, price, stock));
        System.out.println("Product Added Successfully!");
    }



    public List<Product> getAllProducts() throws Exception {
        return dao.getAllProducts();
    }

    public Product getProduct(int id) throws Exception {
        return dao.getProductById(id);
    }

    public void reduceStock(int id, int qty) throws Exception {
        dao.updateStock(id, qty);
    }
}
