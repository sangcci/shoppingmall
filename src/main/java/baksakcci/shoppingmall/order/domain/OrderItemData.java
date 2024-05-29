package baksakcci.shoppingmall.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemData {

    private long productId;
    private String name;
    private int qty;
    private int price;

}
