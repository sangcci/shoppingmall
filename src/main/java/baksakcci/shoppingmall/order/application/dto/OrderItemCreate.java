package baksakcci.shoppingmall.order.application.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderItemCreate {

    private Long productId;
    private int qty;
}
