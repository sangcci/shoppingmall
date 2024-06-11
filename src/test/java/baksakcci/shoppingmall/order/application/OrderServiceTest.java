package baksakcci.shoppingmall.order.application;

import static baksakcci.shoppingmall.order.fixture.OrderFixtureProvider.주문_생성;
import static baksakcci.shoppingmall.order.fixture.OrderFixtureProvider.주문서_생성;
import static baksakcci.shoppingmall.order.fixture.ProductFixtureProvider.상품_꿀사과_생성;
import static baksakcci.shoppingmall.order.fixture.ProductFixtureProvider.상품_안창살_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import baksakcci.shoppingmall.catalog.domain.repository.ProductRepository;
import baksakcci.shoppingmall.order.application.port.OrderRepository;
import baksakcci.shoppingmall.order.application.port.OrderService;
import baksakcci.shoppingmall.order.domain.Order;
import baksakcci.shoppingmall.order.domain.OrderCreate;
import baksakcci.shoppingmall.order.domain.OrderState;
import baksakcci.shoppingmall.order.mock.OrderFakeRepository;
import baksakcci.shoppingmall.order.mock.ProductFakeRepository;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderServiceTest {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceTest.class);
    private OrderService orderService;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    @BeforeEach
    void before() {
        productRepository = new ProductFakeRepository();
        orderRepository = new OrderFakeRepository();
        orderService = new OrderServiceImpl(
                orderRepository,
                productRepository
        );
    }

    @Test
    void 주문을_하면_주문_정보를_저장한다() {
        // given
        productRepository.save(상품_꿀사과_생성());
        productRepository.save(상품_안창살_생성());
        OrderCreate orderCreate = 주문서_생성();

        // when
        long orderId = orderService.create(orderCreate);

        // then
        Order order = orderRepository.findById(orderId);
        assertThat(order.getTotalPrice()).isEqualTo(2000 * 2 + 65000 * 1);
        assertThat(order.getDeliveryInfo().getDetailAddress()).isEqualTo("땡땡빌딩 101호");
        assertThat(order.getOrderItems().get(0).getProduct().getName()).isEqualTo("꿀사과");
    }

    @Test
    void 조회한_상품이_존재하지_않다면_예외를_발생시킨다() {
        // given
        OrderCreate orderCreate = 주문서_생성();

        // when & then
        assertThatThrownBy(() -> orderService.create(orderCreate))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("No product found with id: "  + orderCreate.getOrderItemCreates().get(0).getProductId());
    }

    void 조회한_상품이_품절되었다면_예외를_발생한다() {}

    @Test
    void 주문을_취소한다() {
        long orderId = orderRepository.save(주문_생성(상품_꿀사과_생성(), 상품_안창살_생성()));

        orderService.cancel(orderId);

        assertThat(orderRepository.findById(orderId).getOrderState()).isEqualTo(OrderState.CANCELED);
    }

    @Test
    void 주문을_삭제한다() {
        // given
        long orderId = orderRepository.save(주문_생성(상품_꿀사과_생성(), 상품_안창살_생성()));

        // when
        orderService.delete(orderId);

        // then
        assertThatThrownBy(() -> orderRepository.findById(orderId))
                .isInstanceOf(NoSuchElementException.class);
    }
}
