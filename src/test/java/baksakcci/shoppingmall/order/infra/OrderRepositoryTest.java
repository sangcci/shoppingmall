package baksakcci.shoppingmall.order.infra;

import static baksakcci.shoppingmall.order.fixture.OrderFixtureProvider.주문_생성;
import static baksakcci.shoppingmall.order.fixture.ProductFixtureProvider.상품_꿀사과_생성;
import static baksakcci.shoppingmall.order.fixture.ProductFixtureProvider.상품_안창살_생성;

import baksakcci.shoppingmall.order.application.port.OrderRepository;
import baksakcci.shoppingmall.order.domain.Order;
import java.util.NoSuchElementException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@DataJpaTest
@ActiveProfiles("test")
@Sql(value = "/sql/product-create-repository-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
//@Sql(scripts = "/sql/order-reset-auto-increment.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class OrderRepositoryTest {

    private OrderRepository orderRepository;

    @Autowired
    public OrderRepositoryTest(OrderJpaRepository orderJpaRepository,
            OrderItemJpaRepository orderItemJpaRepository) {
        orderRepository = new OrderRepositoryImpl(orderJpaRepository, orderItemJpaRepository);
    }

    @Test
    //@Sql("/sql/product-create-repository-test-data.sql")
    void save_test() {
        // given
        Order order = 주문_생성(상품_꿀사과_생성(), 상품_안창살_생성());

        // when
        long orderId = orderRepository.save(order);

        // then
        Assertions.assertThat(orderId).isGreaterThan(0);
    }

    @Test
    //@Sql("/sql/product-create-repository-test-data.sql")
    void delete_test() {
        // given
        Order order = 주문_생성(상품_꿀사과_생성(), 상품_안창살_생성());
        long orderId = orderRepository.save(order);

        // when
        orderRepository.deleteById(orderId);

        // then
        Assertions.assertThatThrownBy(() -> orderRepository.findById(orderId))
                .isInstanceOf(NoSuchElementException.class);
    }
}
