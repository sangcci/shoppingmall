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

        OrderData orderData = em.createQuery(
                        "select new baksakcci.shoppingmall.order.domain.OrderData(o.id, o.orderState, o.orderAt)" +
                                " from OrderEntity o" +
                                " where o.id = :orderId", OrderData.class)
                .setParameter("orderId", id)
                .getSingleResult();

        List<OrderItemData> orderItemDataList = queryFactory.select(Projections.constructor(OrderItemData.class,
                        orderItemEntity.productEntity.id,
                        orderItemEntity.name,
                        orderItemEntity.qty,
                        orderItemEntity.price))
                .from(orderItemEntity)
                .where(orderItemEntity.orderEntity.id.eq(id))
                .fetch();

        orderData.setOrderItemDatas(orderItemDataList);
        return orderData;
    }
}
