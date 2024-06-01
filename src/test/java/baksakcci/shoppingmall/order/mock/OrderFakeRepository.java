package baksakcci.shoppingmall.order.mock;

import baksakcci.shoppingmall.order.domain.Order;
import baksakcci.shoppingmall.order.application.port.OrderRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class OrderFakeRepository implements OrderRepository {

    private Long id = 1L;
    private static final Map<Long, Order> orderStorage = new HashMap<>();

    @Override
    public long create(Order order) {
        orderStorage.put(id++, order);
        return id;
    }

    @Override
    public Order findById(long id) {
        return orderStorage.get(id);
    }

    @Override
    public Order update(Order order) {
        if (orderStorage.getOrDefault(order.getId(), null) == null) {
            throw new NoSuchElementException();
        }
        orderStorage.put(order.getId(), order);
        return orderStorage.get(order.getId());
    }
}
