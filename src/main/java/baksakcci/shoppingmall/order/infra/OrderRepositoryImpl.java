package baksakcci.shoppingmall.order.infra;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import baksakcci.shoppingmall.catalog.infra.ProductEntity;
import baksakcci.shoppingmall.catalog.infra.ProductJpaRepository;
import baksakcci.shoppingmall.order.application.port.OrderRepository;
import baksakcci.shoppingmall.order.domain.Order;
import baksakcci.shoppingmall.order.domain.OrderItem;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderItemJpaRepository orderItemJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    @Override
    @Transactional
    public Order create(Order order) {
        OrderEntity orderEntity = OrderEntity.from(order);
        OrderEntity saved = orderJpaRepository.save(orderEntity);
        List<OrderItemEntity> savedItems = order.getOrderItems().stream().map(each -> {
            OrderItemEntity orderItemEntity = OrderItemEntity.from(each);
            return orderItemJpaRepository.save(orderItemEntity);
        }).toList();
        return saved.toModel(toOrderItems(savedItems));
    }

    public List<OrderItem> toOrderItems(List<OrderItemEntity> orderItemEntities) {
        return orderItemEntities.stream().map(each -> each.toModel()).toList();
    }

    @Override
    @Transactional
    public Order update(Order order) {
        return null;
    }

    @Override
    public Order findById(Long id) {
        OrderEntity orderEntity = orderJpaRepository.findById(id).orElseThrow(NoSuchElementException::new);
        List<OrderItemEntity> orderItemEntities = orderItemJpaRepository.findOrderItemEntitiesByOrderEntity(orderEntity);
        List<OrderItem> orderItems = orderItemEntities.stream().map(each -> {
            ProductEntity productEntity = productJpaRepository.findById(each.getId()).orElseThrow(NoSuchElementException::new);
            Product product = productEntity.toModel();
            return OrderItem.builder()
                    .product(product)
                    .qty(each.getQty())
                    .price(product.getPrice())
                    .build();
        }).toList();
        return orderEntity.toModel(orderItems);
    }

    @Override
    public List<Order> findByOrdererId(Long id) {
        return null;
    }

}
