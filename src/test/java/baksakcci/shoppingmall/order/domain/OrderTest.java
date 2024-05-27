package baksakcci.shoppingmall.order.domain;

import static org.assertj.core.api.Assertions.assertThat;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderTest {

    @Nested
    class 주문_생성 {

        @Test
        void 올바른_주문정보가_들어오면_주문_객체를_생성한다() {
            // given
            Product productA = productFixtureA();
            Product productB = productFixtureB();

            List<OrderItem> orderItems = orderItemFixture(productA, productB);
            DeliveryInfo deliveryInfo = deliveryInfoFixture();

            // when
            Order order = new Order(orderItems, deliveryInfo, OrderState.PAYMENT_WAITING);

            // then
            assertThat(order.getOrderItems().get(0).getQty()).isEqualTo(2);
            assertThat(order.getTotalPrice()).isEqualTo(2000 * 2 + 65000 * 3);
            assertThat(order.getOrderState()).isEqualTo(OrderState.PAYMENT_WAITING);
        }

        static Stream<Arguments> provideDeliveryInfo() {
            return Stream.of(
                    Arguments.of("경기도", "1201호", "박상혁", null)
                    );
        }
        @ParameterizedTest(name = "{0}")
        @MethodSource("provideDeliveryInfo")
        void 배송지_정보를_하나라도_입력하지_않으면_예외를_발생시킨다(String address, String detailAddress,
                String receiverName, String receiverPhoneNumber) {

        }

        void 주문자_정보가_잘못되면_예외를_발생한다() {}

        void 상품_정보가_잘못되면_예외를_발생한다() {}

        void 결제에_오류가_발생하면_주문을_생성하지_않는다() {}
    }

    @Nested
    class 주문_내역_수정 {

        void 주문_상품을_추가할_수_없다() {}

        void 출고_전일때는_주문내역을_수정할_수_있다() {}

        void 배송_중일때는_주문내역을_수정할_수_없다() {}
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

    List<OrderItem> orderItemFixture(Product productA, Product productB) {
        OrderItem itemA = OrderItem.builder()
                .product(productA)
                .qty(2)
                .price(2000)
                .build();
        OrderItem itemB = OrderItem.builder()
                .product(productB)
                .qty(3)
                .price(65000)
                .build();
        List<OrderItem> items = new ArrayList<>();
        items.add(itemA);
        items.add(itemB);
        return items;
    }

    DeliveryInfo deliveryInfoFixture() {
        return DeliveryInfo.builder()
                .address("경기도")
                .detailAddress("땡땡빌딩 101호")
                .receiverName("박상혁")
                .receiverPhoneNumber("010-1234-5678")
                .build();
    }
}
