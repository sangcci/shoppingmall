package baksakcci.shoppingmall.order.infra;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import baksakcci.shoppingmall.order.domain.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

}
