package baksakcci.shoppingmall.order.application.port;

import baksakcci.shoppingmall.order.domain.Order;

public interface OrderRepository {

    long save(Order order);

    Order findById(long id);

}
