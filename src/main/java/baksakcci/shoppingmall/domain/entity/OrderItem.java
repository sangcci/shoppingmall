package baksakcci.shoppingmall.domain.entity;

public class OrderItem {

    private long id;
    private int price;
    private int qty;
    private Product product;

    // method, role
    public int calculateAmounts() {
        return price * qty;
    }

    // constructor
    public OrderItem(long id, int price, int qty, Product product) {
        this.id = id;
        this.price = price;
        this.qty = qty;
        this.product = product;
    }

    public OrderItem(long id, int qty, Product product) {
        this.id = id;
        this.price = product.getPrice();
        this.qty = qty;
        this.product = product;
    }
}
