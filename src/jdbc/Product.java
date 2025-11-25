package jdbc;
class product {
    int id;
    String name;
    double price;
    int stock;

    product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String toString() {
        return id + " | " + name + " | " + price + " | " + stock;
    }
}
