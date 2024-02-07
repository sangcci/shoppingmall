package baksakcci.shoppingmall.order.unitTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import baksakcci.shoppingmall.order.domain.entity.Order;
import baksakcci.shoppingmall.order.domain.entity.OrderItem;
import baksakcci.shoppingmall.catalog.domain.entity.Product;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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

            // when
            Order order = new Order(orderItems);

            // then
            assertThat(order).isInstanceOf(Order.class);
        }

        @Test
        @DisplayName("[EXCEPTION] 상품 리스트에 아무것도 없을 경우 예외 발생")
        void orderItems_emptyOrNull_throwException() {
            // give
            ArrayList<OrderItem> orderItems = new ArrayList<>();

            // when & then
            assertThatThrownBy(() -> new Order(orderItems))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("no order items.");
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

            // when
            Order order = new Order(orderItems);

            // then
            assertThat(order.getTotalPrice()).isEqualTo(10000 * 3 + 15000 * 4);
        }
    }

}
