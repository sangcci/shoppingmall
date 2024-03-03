package baksakcci.shoppingmall.order.domain.entity;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class OrderItem {

    private int price;
    private int qty;
    private Long productId;

    // method, role
    public int calculateAmounts() {
        return price * qty;
    }
}
