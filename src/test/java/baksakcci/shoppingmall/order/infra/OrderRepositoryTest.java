package baksakcci.shoppingmall.order.infra;

import static baksakcci.shoppingmall.order.fixture.OrderFixtureProvider.주문_생성;
import static baksakcci.shoppingmall.order.fixture.ProductFixtureProvider.상품_생성;

import baksakcci.shoppingmall.order.application.port.OrderRepository;
import baksakcci.shoppingmall.order.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@ActiveProfiles("test")
public class OrderRepositoryTest {

    private OrderRepository orderRepository;

    @Autowired
    public OrderRepositoryTest(OrderJpaRepository orderJpaRepository,
            OrderItemJpaRepository orderItemJpaRepository) {
        orderRepository = new OrderRepositoryImpl(orderJpaRepository, orderItemJpaRepository);
    }

    @Test
    @Sql("/sql/product-create-repository-test-data.sql")
    void save_test() {
        // given
        Order order = 주문_생성(상품_생성());

        // when
        long orderId = orderRepository.save(order);

        // then
        Assertions.assertThat(orderId).isGreaterThan(0);
    }
}
