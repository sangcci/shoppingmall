package baksakcci.shoppingmall.order.domain;

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
    private Product product;

    // method, role
    public int calculateAmounts() {
        return price * qty;
    }
}
