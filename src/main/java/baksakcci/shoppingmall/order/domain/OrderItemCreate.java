package baksakcci.shoppingmall.order.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderItemCreate {

    private Long productId;
    private int qty;

}
