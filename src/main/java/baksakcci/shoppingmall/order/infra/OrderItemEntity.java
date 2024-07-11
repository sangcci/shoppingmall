package baksakcci.shoppingmall.order.infra;

import baksakcci.shoppingmall.catalog.infra.ProductEntity;
import baksakcci.shoppingmall.order.domain.OrderItem;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "order_items")
@Getter
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int price;
    private int qty;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    public static OrderItemEntity from(OrderItem orderItem, OrderEntity orderEntity) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.name = orderItem.getProduct().getName();
        orderItemEntity.price = orderItem.getPrice();
        orderItemEntity.qty = orderItem.getQty();
        orderItemEntity.orderEntity = orderEntity;
        orderItemEntity.productEntity = ProductEntity.from(orderItem.getProduct());
        return orderItemEntity;
    }

    public OrderItem toModel() {
        return OrderItem.builder()
                .price(price)
                .qty(qty)
                .product(productEntity.toModel())
                .build();
    }
}
