package baksakcci.shoppingmall.order.application.port;

import baksakcci.shoppingmall.order.application.dto.OrderCreate;
import baksakcci.shoppingmall.order.domain.Order;

public interface PlaceOrderService {

    Order placeOrder(OrderCreate orderCreate);
}
