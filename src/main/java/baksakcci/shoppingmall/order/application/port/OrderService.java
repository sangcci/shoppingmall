package baksakcci.shoppingmall.order.application.port;

import baksakcci.shoppingmall.order.domain.OrderCreate;

public interface OrderService {

    void create(OrderCreate orderCreate);

}
