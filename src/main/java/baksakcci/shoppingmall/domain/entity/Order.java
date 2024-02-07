package baksakcci.shoppingmall.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private long id;
    private List<OrderItem> orderItems;
    private int totalPrice;
    private LocalDateTime orderAt;
    private User user;

    // constructor
    public Order(List<OrderItem> orderItems) {
        verifyAtLeastOneOrMoreOrderItems(orderItems);
        this.orderItems = orderItems;
        calculateTotalPrice();
    }

    // method, role
    public void changeShipped() {}
    public void changeShippingInfo() {}
    public void cancel() {}
    public void completePayment() {}
    private void calculateTotalPrice() {
        this.totalPrice = orderItems.stream()
                .mapToInt(OrderItem::calculateAmounts)
                .sum();
    }

    // validation
    private void verifyAtLeastOneOrMoreOrderItems(List<OrderItem> orderItems) {
        // exception handler
        if (orderItems == null || orderItems.isEmpty()) {
            throw new IllegalArgumentException("no order items.");
        }
    }

    // association method (caused JPA)
}
