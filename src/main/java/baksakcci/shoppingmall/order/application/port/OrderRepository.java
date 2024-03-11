package baksakcci.shoppingmall.order.application.port;

import baksakcci.shoppingmall.order.domain.Order;
import java.util.List;

public interface OrderRepository {

    Order create(Order order);

    Order update(Order order);

    Order findById(Long id);

    List<Order> findByOrdererId(Long id);
}
