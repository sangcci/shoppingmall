package baksakcci.shoppingmall.order.infra;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, Long> {

    List<OrderItemEntity> findAllByOrderEntityId(long orderEntityId);
    List<OrderItemEntity> findOrderItemEntitiesByOrderEntity(OrderEntity orderEntity);
    void deleteAllByOrderEntityId(long orderEntityId);
}