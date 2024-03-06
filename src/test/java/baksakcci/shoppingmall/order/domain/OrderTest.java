package baksakcci.shoppingmall.order.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
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
            /* 해당 코드는 다른 도메인 영역에 의존함
            Product product = Product.builder()
                    .id(1)
                    .name("바지")
                    .manufacturer("유니클로")
                    .price(10000)
                    .build();
            */
            // given
            OrderItem orderItem = OrderItem.builder()
                    .productId(1L)
                    .qty(3)
                    .price(10000)
                    .build();
            ArrayList<OrderItem> orderItems = new ArrayList<>();
            orderItems.add(orderItem);

            DeliveryInfo deliveryInfo = DeliveryInfo.builder()
                    .address("경기도")
                    .detailAddress("1201호")
                    .receiverName("박상혁")
                    .receiverPhoneNumber("010-1234-5678")
                    .build();

            // when
            Order order = new Order(orderItems, deliveryInfo, OrderState.PAYMENT_WAITING);

            // then
            assertThat(order.getOrderItems().get(0).getQty()).isEqualTo(3);
            assertThat(order.getTotalPrice()).isEqualTo(10000 * 3);
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
}
