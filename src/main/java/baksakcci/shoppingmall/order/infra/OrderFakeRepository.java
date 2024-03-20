package baksakcci.shoppingmall.order.infra;

import baksakcci.shoppingmall.order.domain.Order;
import baksakcci.shoppingmall.order.application.port.OrderRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class OrderFakeRepository implements OrderRepository {

    private Long id = 1L;
    private static final Map<Long, Order> orders = new HashMap<>();

    @Override
    public Order create(Order order) {
        orders.put(id, order);
        return orders.get(id++);
    }

    @Override
    public Order update(Order order) {
        if (orders.getOrDefault(order.getId(), null) == null) {
            throw new NoSuchElementException();
        }
        orders.put(order.getId(), order);
        return orders.get(order.getId());
    }

    @Override
    public Order findById(Long id) {
        return orders.get(id);
    }

    @Override
    public List<Order> findByOrdererId(Long id) {
        return null;
    }
}
