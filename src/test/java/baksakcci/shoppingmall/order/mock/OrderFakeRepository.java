package baksakcci.shoppingmall.order.mock;

import baksakcci.shoppingmall.order.application.port.OrderRepository;
import baksakcci.shoppingmall.order.domain.Order;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class OrderFakeRepository implements OrderRepository {

    private Long id = 1L;
    private static final Map<Long, Order> orderStorage = new HashMap<>();

    @Override
    public long save(Order order) {
        if (order.getId() == 0) {
            Order newOrder = Order.builder()
                    .id(id)
                    .orderItems(order.getOrderItems())
                    .orderer(order.getOrderer())
                    .totalPrice(order.getTotalPrice())
                    .deliveryInfo(order.getDeliveryInfo())
                    .orderState(order.getOrderState())
                    .orderAt(order.getOrderAt())
                    .build();
            orderStorage.put(id, newOrder);
        }
        orderStorage.put(order.getId(), order);
        return id++;
    }

    @Override
    public Order findById(long id) {
        Order order = orderStorage.getOrDefault(id, null);
        if (order == null) {
            throw new NoSuchElementException();
        }
        return orderStorage.get(id);
    }

    @Override
    public void deleteById(long id) {
        orderStorage.remove(id);
    }

}
