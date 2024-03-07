package baksakcci.shoppingmall.order.infra;

import baksakcci.shoppingmall.order.domain.DeliveryInfo;
import baksakcci.shoppingmall.order.domain.Order;
import baksakcci.shoppingmall.order.domain.OrderItem;
import baksakcci.shoppingmall.order.domain.OrderState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "total_price")
    private int totalPrice;
    @Column(name = "address")
    private String address;
    @Column(name = "detail_address")
    private String detailAddress;
    @Column(name = "receiver_name")
    private String receiverName;
    @Column(name = "receiver_phone_number")
    private String receiverPhoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_state")
    private OrderState orderState;
    @Column(name = "order_at")
    private LocalDateTime orderAt;

    public static OrderEntity from(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.id = order.getId();
        orderEntity.totalPrice = order.getTotalPrice();
        orderEntity.address = order.getDeliveryInfo().getAddress();
        orderEntity.detailAddress = order.getDeliveryInfo().getDetailAddress();
        orderEntity.receiverName = order.getDeliveryInfo().getReceiverName();
        orderEntity.receiverPhoneNumber = order.getDeliveryInfo().getReceiverPhoneNumber();
        orderEntity.orderState = order.getOrderState();
        orderEntity.orderAt = order.getOrderAt();
        return orderEntity;
    }

    public Order toModel(List<OrderItem> orderItems) {
        DeliveryInfo deliveryInfo = DeliveryInfo.builder()
                .address(address)
                .detailAddress(detailAddress)
                .receiverName(receiverName)
                .receiverPhoneNumber(receiverPhoneNumber)
                .build();
        return Order.builder()
                .id(id)
                .orderItems(orderItems)
                .totalPrice(totalPrice)
                .deliveryInfo(deliveryInfo)
                .orderState(orderState)
                .orderAt(orderAt)
                .build();
    }
}
