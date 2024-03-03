package baksakcci.shoppingmall.order.application.port;

import baksakcci.shoppingmall.order.domain.entity.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    void create(Order order);

    void update(Order order);

    Order findById(Long id);

    List<Order> findByOrdererId(Long id);
}
