package baksakcci.shoppingmall.order.infra;

import baksakcci.shoppingmall.order.domain.entity.Order;
import baksakcci.shoppingmall.order.application.port.OrderRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OrderFakeRepository implements OrderRepository {

    private Long id = 0L;
    private static final Map<Long, Order> orders = new HashMap<>();

    @Override
    public void create(Order order) {
        orders.put(id, order);
        id += 1L;
    }

    @Override
    public void update(Order order) {
        if (orders.getOrDefault(order.getId(), null) == null) {
            throw new NoSuchElementException();
        }
        orders.put(order.getId(), order);
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
