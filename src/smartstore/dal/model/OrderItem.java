package smartstore.dal.model;

public class OrderItem {
    
    public int productId;
    public String productName;
    public int quantity;
    public double totalPrice;

    public OrderItem(int productId, String productName, int quantity, double totalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return productId + " | " + productName + " | Qty: " + quantity + " | Total: " + totalPrice;
    }
}

