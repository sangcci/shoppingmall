package baksakcci.shoppingmall.order.domain.entity;

import baksakcci.shoppingmall.member.domain.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

public class Order {

    private long id;
    private List<OrderItem> orderItems;
    @Getter
    private int totalPrice;
    private DeliveryInfo deliveryInfo;
    private LocalDateTime orderAt;
    private User user;

    // constructor
    public Order(List<OrderItem> orderItems, DeliveryInfo deliveryInfo) {
        setOrderItems(orderItems);
        setDeliveryInfo(deliveryInfo);
        this.orderAt = LocalDateTime.now();
    }

    private void setOrderItems(List<OrderItem> orderItems) {
        verifyAtLeastOneOrMoreOrderItems(orderItems);
        this.orderItems = orderItems;
        calculateTotalPrice();
    }

    private void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        verifyNullDeliveryInfo(deliveryInfo);
        this.deliveryInfo = deliveryInfo;
    }

    // method, role, utility
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

    private void verifyNullDeliveryInfo(DeliveryInfo deliveryInfo) {
        if (deliveryInfo == null) {
            throw new IllegalArgumentException("no delivery info.");
        }
    }

    // association method (caused JPA)
}
