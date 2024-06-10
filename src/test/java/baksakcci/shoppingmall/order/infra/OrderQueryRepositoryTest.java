package baksakcci.shoppingmall.order.infra;

import static org.assertj.core.api.Assertions.assertThat;

import baksakcci.shoppingmall.config.QuerydslConfig;
import baksakcci.shoppingmall.order.domain.OrderData;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@DataJpaTest
@ActiveProfiles("test")
@Import(QuerydslConfig.class)
@Sql(value = {"/sql/product-create-repository-test-data.sql", "/sql/order-create-repository-test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class OrderQueryRepositoryTest {

    private OrderQueryRepository orderQueryRepository;

    @Autowired
    public OrderQueryRepositoryTest(JPAQueryFactory jpaQueryFactory) {
        orderQueryRepository = new OrderQueryRepository(jpaQueryFactory);
    }

    @Test
    void findById_test() {
        // given
        long orderId = 1L;
        // when
        OrderData orderData = orderQueryRepository.findById(orderId);

        // then
        assertThat(orderData.getId()).isEqualTo(orderId);
    }

    @Test
    void findAllByPagination_test() {
        // given
        int page = 0;
        int size = 10;

        // when
        List<OrderData> orderDataList = orderQueryRepository.findAllByPagination(page, size);

        // then
        OrderData orderData = orderDataList.get(0);
        assertThat(orderData.getId()).isEqualTo(1L);
    }
}
