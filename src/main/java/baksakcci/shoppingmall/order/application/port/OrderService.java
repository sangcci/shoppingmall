package baksakcci.shoppingmall.order.application.port;

import baksakcci.shoppingmall.order.domain.OrderCreate;

public interface OrderService {

    long create(OrderCreate orderCreate);

    void cancel(long orderId);

    void delete(long orderId);
}
