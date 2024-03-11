package baksakcci.shoppingmall.order.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class OrderItemRequest {

    private int qty;
    private long productId;
}
