package jdbc;
class OrderItem {
    int productId;
    String productName;
    int qty;
    double totalPrice;

    OrderItem(int productId, String productName, int qty, double totalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.qty = qty;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Product: " + productName +
               ", Qty: " + qty +
               ", Total: " + totalPrice;
    }
}
