package baksakcci.shoppingmall.order.infra;

import static baksakcci.shoppingmall.order.infra.QOrderItemEntity.orderItemEntity;

import baksakcci.shoppingmall.order.domain.OrderData;
import baksakcci.shoppingmall.order.domain.OrderData.OrderItemData;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    @PersistenceContext
    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public OrderData findById(long id) {
        return queryFactory.selectFrom(orderEntity)
                .join(orderItemEntity).on(orderItemEntity.id.eq(orderEntity.id))
                .where(orderEntity.id.eq(id))
                .transform(groupBy(orderEntity.id).as(Projections.constructor(OrderData.class,
                        orderEntity.id,
                        orderEntity.orderState,
                        orderEntity.orderAt,
                        list(Projections.constructor(OrderItemData.class,
                                orderItemEntity.productEntity.id,
                                orderItemEntity.name,
                                orderItemEntity.qty,
                                orderItemEntity.price
                        ))
                )))
                .get(id);
    }
}
