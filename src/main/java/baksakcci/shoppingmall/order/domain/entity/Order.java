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
    private OrderState orderState;
    private LocalDateTime orderAt;
    private User user;

    // constructor
    public Order(List<OrderItem> orderItems, DeliveryInfo deliveryInfo, OrderState orderState) {
        setOrderItems(orderItems);
        setDeliveryInfo(deliveryInfo);
        this.orderState = orderState;
        this.orderAt = LocalDateTime.now();
    }

    // method, role, utility
    public void changeShipped() {}

    public void changeShippingInfo(DeliveryInfo deliveryInfo) {
        verifyIsPAYMENT_WAITING();
        setDeliveryInfo(deliveryInfo);
    }

    public void cancel() {
        verifyIsPAYMENT_WAITING();
        this.orderState = OrderState.CANCELED;
    }

    public void completePayment() {}

    private void setOrderItems(List<OrderItem> orderItems) {
        verifyAtLeastOneOrMoreOrderItems(orderItems);
        this.orderItems = orderItems;
        calculateTotalPrice();
    }

    private void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        verifyNullDeliveryInfo(deliveryInfo);
        this.deliveryInfo = deliveryInfo;
    }

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

    private void verifyIsPAYMENT_WAITING() {
        if (orderState != OrderState.PAYMENT_WAITING && orderState != OrderState.ITEM_PREPARING) {
            throw new IllegalStateException("already Shipped");
        }
    }

    // association method (caused JPA)
}
