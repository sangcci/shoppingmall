package baksakcci.shoppingmall.order.infra;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, Long> {

    List<OrderItemEntity> findOrderItemEntitiesByOrderEntityId(long orderEntityId);
    List<OrderItemEntity> findOrderItemEntitiesByOrderEntity(OrderEntity orderEntity);

}