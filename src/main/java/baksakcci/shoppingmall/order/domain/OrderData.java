package baksakcci.shoppingmall.order.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class OrderData {

    private long id;
    private OrderState orderState;
    private LocalDateTime orderAt;
    @Setter
    private List<OrderItemData> orderItemDatas;

    public OrderData(long id, OrderState orderState, LocalDateTime orderAt) {
        this.id = id;
        this.orderState = orderState;
        this.orderAt = orderAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class OrderItemData {

        private long productId;
        private String name;
        private int qty;
        private int price;

    }
}
