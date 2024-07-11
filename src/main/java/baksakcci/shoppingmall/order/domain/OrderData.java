package baksakcci.shoppingmall.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
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
