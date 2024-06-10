package baksakcci.shoppingmall.order.infra;

import static baksakcci.shoppingmall.order.infra.QOrderEntity.orderEntity;
import static baksakcci.shoppingmall.order.infra.QOrderItemEntity.orderItemEntity;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import baksakcci.shoppingmall.order.domain.OrderData;
import baksakcci.shoppingmall.order.domain.OrderData.OrderItemData;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public List<OrderData> findAllByPagination(int page, int size) {
        return queryFactory.selectFrom(orderEntity)
                .leftJoin(orderItemEntity).on(orderEntity.id.eq(orderItemEntity.orderEntity.id))
                .offset((long) page * size)
                .limit(size)
                .transform(
                        groupBy(orderEntity.id).list(
                                Projections.constructor(OrderData.class,
                                        orderEntity.id,
                                        orderEntity.orderState,
                                        orderEntity.orderAt,
                                        list(Projections.constructor(OrderItemData.class,
                                                orderItemEntity.productEntity.id,
                                                orderItemEntity.name,
                                                orderItemEntity.qty,
                                                orderItemEntity.price
                                        ))
                                )));
    }
}
