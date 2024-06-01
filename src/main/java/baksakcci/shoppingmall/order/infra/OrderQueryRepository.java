package baksakcci.shoppingmall.order.infra;

import baksakcci.shoppingmall.order.domain.OrderData;
import baksakcci.shoppingmall.order.domain.OrderItemData;
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

    public OrderData findById(long id) {

        OrderData orderData = em.createQuery(
                        "select new baksakcci.shoppingmall.order.domain.OrderData(o.id, o.orderState, o.orderAt)" +
                                " from OrderEntity o" +
                                " where o.id = :orderId", OrderData.class)
                .setParameter("orderId", id)
                .getSingleResult();

        List<OrderItemData> orderItemDataList = em.createQuery(
                        "select new baksakcci.shoppingmall.order.domain.OrderItemData(oi.productEntity.id, oi.name, oi.qty, oi.price)" +
                                " from OrderItemEntity oi" +
                                " where oi.orderEntity.id = :orderId", OrderItemData.class)
                .setParameter("orderId", id)
                .getResultList();

        orderData.setOrderItemDatas(orderItemDataList);
        return orderData;
    }
}
