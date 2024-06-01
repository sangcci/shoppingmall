package baksakcci.shoppingmall.order.application.port;

import baksakcci.shoppingmall.order.domain.Order;

public interface OrderRepository {

    long create(Order order);

    Order findById(long id);

    Order update(Order order);

}
