package baksakcci.shoppingmall.order.unitTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import baksakcci.shoppingmall.order.domain.entity.DeliveryInfo;
import baksakcci.shoppingmall.order.domain.entity.Order;
import baksakcci.shoppingmall.order.domain.entity.OrderItem;
import baksakcci.shoppingmall.catalog.domain.entity.Product;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("[Order] 주문 도메인 테스트")
public class OrderTest {

    @Nested
    @DisplayName("[new] 주문 객체 생성 테스트")
    class newTest {

        @Test
        @DisplayName("[SUCCESS] 상품 리스트를 넘겨주면 주문 객체를 생성한다")
        void create_order_test() {
            // give
            Product pants = new Product(1, "바지", "유니클로", 10000);

            OrderItem orderItem = new OrderItem(1, 3, pants);
            ArrayList<OrderItem> orderItems = new ArrayList<>();
            orderItems.add(orderItem);

            DeliveryInfo deliveryInfo = new DeliveryInfo("bak",
                    "010-1234-5678",
                    "경기도",
                    "1201호");

            // when
            Order order = new Order(orderItems, deliveryInfo);

            // then
            assertThat(order).isInstanceOf(Order.class);
        }

        static Stream<Arguments> provideOrders() {
            Product pants = new Product(1, "바지", "유니클로", 10000);
            Product hoodie = new Product(2, "후드티", "H&M", 15000);

            OrderItem orderItem1 = new OrderItem(1, 3, pants);
            OrderItem orderItem2 = new OrderItem(2, 4, hoodie);

            ArrayList<OrderItem> orderItems = new ArrayList<>();
            orderItems.add(orderItem1);
            orderItems.add(orderItem2);

            DeliveryInfo deliveryInfo = new DeliveryInfo("bak",
                    "010-1234-5678",
                    "경기도",
                    "1201호");
            return Stream.of(
                    Arguments.of("no order items.",
                            new ArrayList<OrderItem>(),
                            deliveryInfo),
                    Arguments.of("no delivery info.",
                            orderItems,
                            null
                    )
            );
        }

        @DisplayName("[EXCEPTION] 상품 생성자 테스트")
        @ParameterizedTest(name = "{index}: {0}")
        @MethodSource("provideOrders")
        void orderItems_emptyOrNull_throwException(String message, ArrayList<OrderItem> orderItems, DeliveryInfo deliveryInfo) {
            // when & then
            assertThatThrownBy(() -> new Order(orderItems, deliveryInfo))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(message);
        }

        @Test
        @DisplayName("[SUCCESS] 주문한 상품들의 가격 총 합계를 구한다")
        void totalAmount_test() {
            // give
            Product pants = new Product(1, "바지", "유니클로", 10000);
            Product hoodie = new Product(2, "후드티", "H&M", 15000);

            OrderItem orderItem1 = new OrderItem(1, 3, pants);
            OrderItem orderItem2 = new OrderItem(2, 4, hoodie);

            ArrayList<OrderItem> orderItems = new ArrayList<>();
            orderItems.add(orderItem1);
            orderItems.add(orderItem2);

            DeliveryInfo deliveryInfo = new DeliveryInfo("bak",
                    "010-1234-5678",
                    "경기도",
                    "1201호");

            // when
            Order order = new Order(orderItems, deliveryInfo);

            // then
            assertThat(order.getTotalPrice()).isEqualTo(10000 * 3 + 15000 * 4);
        }
    }

}
