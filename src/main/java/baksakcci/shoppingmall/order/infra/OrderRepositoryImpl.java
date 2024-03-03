package baksakcci.shoppingmall.order.infra;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import baksakcci.shoppingmall.catalog.infra.ProductEntity;
import baksakcci.shoppingmall.catalog.infra.ProductJpaRepository;
import baksakcci.shoppingmall.order.application.port.OrderRepository;
import baksakcci.shoppingmall.order.domain.entity.Order;
import baksakcci.shoppingmall.order.domain.entity.OrderItem;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderItemJpaRepository orderItemJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    @Override
    public void create(Order order) {
        OrderEntity orderEntity = OrderEntity.from(order);
        orderJpaRepository.save(orderEntity);
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public Order findById(Long id) {
        OrderEntity orderEntity = orderJpaRepository.findById(id).orElseThrow(NoSuchElementException::new);
        List<OrderItemEntity> orderItemEntities = orderItemJpaRepository.findOrderItemEntitiesByOrderEntity(orderEntity);
        List<OrderItem> orderItems = orderItemEntities.stream().map(each -> {
            ProductEntity productEntity = productJpaRepository.findById(each.getId()).orElseThrow(NoSuchElementException::new);
            Product product = productEntity.toModel();
            return OrderItem.builder()
                    .productId(product.getId())
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
