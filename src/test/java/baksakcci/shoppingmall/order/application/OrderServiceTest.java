package baksakcci.shoppingmall.order.application;

import static org.assertj.core.api.Assertions.assertThat;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import baksakcci.shoppingmall.catalog.domain.repository.ProductRepository;
import baksakcci.shoppingmall.order.application.port.OrderRepository;
import baksakcci.shoppingmall.order.domain.Order;
import baksakcci.shoppingmall.order.mock.ProductFakeRepository;
import baksakcci.shoppingmall.order.application.port.OrderService;
import baksakcci.shoppingmall.order.domain.OrderCreate;
import baksakcci.shoppingmall.order.domain.OrderCreate.OrderItemCreate;
import baksakcci.shoppingmall.order.mock.OrderFakeRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

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
        productRepository.save(productFixtureA());
        productRepository.save(productFixtureB());
        OrderCreate orderCreate = orderCreateFixture();

        // when
        long orderId = orderService.create(orderCreate);

        // then
        Order order = orderRepository.findById(orderId);
        assertThat(order.getTotalPrice()).isEqualTo(2000 * 2 + 65000 * 3);
        assertThat(order.getDeliveryInfo().getDetailAddress()).isEqualTo("땡땡빌딩 101호");
        assertThat(order.getOrderItems().get(0).getProduct().getName()).isEqualTo("꿀사과");
    }

    @Test
    void 조회한_상품이_존재하지_않거나_품절되었다면_예외를_발생한다() {

    }

    Product productFixtureA() {
        return Product.builder()
                .id(1L)
                .name("꿀사과")
                .manufacturer("영주시")
                .price(2000)
                .date(LocalDate.now())
                .build();
    }

    Product productFixtureB() {
        return Product.builder()
                .id(2L)
                .name("안창살")
                .manufacturer("영주한우")
                .price(65000)
                .date(LocalDate.now())
                .build();
    }

    OrderCreate orderCreateFixture() {
        OrderItemCreate item1 = OrderItemCreate.builder()
                .productId(1L)
                .qty(2)
                .build();
        OrderItemCreate item2 = OrderItemCreate.builder()
                .productId(2L)
                .qty(3)
                .build();
        ArrayList<OrderItemCreate> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        return OrderCreate.builder()
                .orderItemCreates(items)
                .address("경기도")
                .detailAddress("땡땡빌딩 101호")
                .receiverName("홍길동")
                .receiverPhoneNumber("010-1234-5678")
                .build();
    }

}
