package baksakcci.shoppingmall.order.application;

import static org.assertj.core.api.Assertions.assertThat;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import baksakcci.shoppingmall.catalog.infra.ProductFakeRepository;
import baksakcci.shoppingmall.order.domain.Order;
import baksakcci.shoppingmall.order.application.dto.OrderCreate;
import baksakcci.shoppingmall.order.domain.OrderItem;
import baksakcci.shoppingmall.order.application.dto.OrderItemCreate;
import baksakcci.shoppingmall.order.infra.OrderFakeRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlaceOrderServiceTest {

    private PlaceOrderServiceImpl placeOrderService;
    private OrderCreate orderCreate;

    @BeforeEach
    void initOrderCreate() {
        OrderFakeRepository orderFakeRepository = new OrderFakeRepository();
        ProductFakeRepository productFakeRepository = new ProductFakeRepository();
        placeOrderService = new PlaceOrderServiceImpl(
                orderFakeRepository, productFakeRepository
        );

        productFakeRepository.save(Product.builder()
                .id(1L)
                .name("바지")
                .manufacturer("유니클로")
                .price(10000)
                .date(LocalDate.now())
                .build());
        productFakeRepository.save(Product.builder()
                .id(2L)
                .name("티셔츠")
                .manufacturer("유니클로")
                .price(5000)
                .date(LocalDate.now())
                .build());

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

        orderCreate = OrderCreate.builder()
                .orderItemCreates(items)
                .address("경기도")
                .detailAddress("땡땡빌딩 101호")
                .receiverName("홍길동")
                .receiverPhoneNumber("010-1234-5678")
                .ordererId(1L)
                .build();
    }

    @Test
    void 주문을_하면_주문_정보를_저장한다() {
        // given, when
        Order order = placeOrderService.placeOrder(orderCreate);

        // then
        OrderItem item1 = order.getOrderItems().get(0);
        assertThat(item1.getPrice()).isEqualTo(10000);
        assertThat(order.getTotalPrice()).isEqualTo(10000 * 2 + 5000 * 3);
        assertThat(order.getDeliveryInfo().getDetailAddress()).isEqualTo("땡땡빌딩 101호");
    }

    @Test
    void 조회한_상품이_존재하지_않거나_품절되었다면_예외를_발생한다() {

    }

}
