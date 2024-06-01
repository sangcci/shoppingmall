package baksakcci.shoppingmall.order.infra;

import baksakcci.shoppingmall.order.application.port.OrderRepository;
import baksakcci.shoppingmall.order.domain.Order;
import baksakcci.shoppingmall.order.domain.OrderItem;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderItemJpaRepository orderItemJpaRepository;

    @Override
    public long create(Order order) {
        OrderEntity orderEntity = OrderEntity.from(order);
        orderJpaRepository.save(orderEntity);
        order.getOrderItems().forEach(each -> {
            OrderItemEntity orderItemEntity = OrderItemEntity.from(each, orderEntity);
            orderItemJpaRepository.save(orderItemEntity);
        });
        return orderEntity.getId();
    }

    @Override
    public Order findById(long id) {
        OrderEntity orderEntity = orderJpaRepository.findById(id).orElseThrow(NoSuchElementException::new);
        List<OrderItemEntity> orderItemEntities = orderItemJpaRepository.findAllByOrderEntityId(id);
        List<OrderItem> orderItems = orderItemEntities.stream().map(OrderItemEntity::toModel).toList();
        return orderEntity.toModel(orderItems);
    }

    @Override
    public Order update(Order order) {
        return null;
    }

}
