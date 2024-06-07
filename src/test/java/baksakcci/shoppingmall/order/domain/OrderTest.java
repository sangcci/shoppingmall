package baksakcci.shoppingmall.order.domain;

import static baksakcci.shoppingmall.order.fixture.OrderFixtureProvider.주문_생성;
import static baksakcci.shoppingmall.order.fixture.OrderFixtureProvider.주문_생성_배송_중_상태;
import static baksakcci.shoppingmall.order.fixture.ProductFixtureProvider.상품_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    void 올바른_주문정보가_들어오면_주문_객체를_생성한다() {
        // given
        Product product = 상품_생성();

        // when
        Order order = 주문_생성(product);

        // then
        assertThat(order.getOrderItems().get(0).getQty()).isEqualTo(2);
        assertThat(order.getTotalPrice()).isEqualTo(2000 * 2);
        assertThat(order.getOrderState()).isEqualTo(OrderState.PAYMENT_WAITING);
    }

    @Test
    void 주문_상태가_결제_전이라면_주문을_취소한다() {
        // given
        Product product = 상품_생성();
        Order order = 주문_생성(product);

        // when
        order.cancel();

        // then
        assertThat(order.getOrderState()).isEqualTo(OrderState.CANCELED);
    }

    @Test
    void 주문_상태가_결제_후라면_주문을_취소하지_못한다() {
        Product product = 상품_생성();
        Order order = 주문_생성_배송_중_상태(product);

        assertThatThrownBy(order::cancel)
                .isInstanceOf(IllegalStateException.class);
    }

    void 주문자_정보가_잘못되면_예외를_발생한다() {
    }

    void 상품_정보가_잘못되면_예외를_발생한다() {
    }

    void 결제에_오류가_발생하면_주문을_생성하지_않는다() {
    }

    void 주문_상품을_추가할_수_없다() {
    }

    void 출고_전일때는_주문내역을_수정할_수_있다() {
    }

    void 배송_중일때는_주문내역을_수정할_수_없다() {
    }

}
