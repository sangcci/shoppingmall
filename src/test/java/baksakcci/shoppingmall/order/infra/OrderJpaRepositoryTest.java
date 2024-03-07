package baksakcci.shoppingmall.order.infra;

import static org.assertj.core.api.Assertions.assertThat;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import baksakcci.shoppingmall.catalog.infra.ProductJpaRepository;
import baksakcci.shoppingmall.order.application.port.OrderRepository;
import baksakcci.shoppingmall.order.domain.DeliveryInfo;
import baksakcci.shoppingmall.order.domain.Order;
import baksakcci.shoppingmall.order.domain.OrderItem;
import baksakcci.shoppingmall.order.domain.OrderState;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase
@DataJpaTest
public class OrderJpaRepositoryTest {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderJpaRepositoryTest(OrderJpaRepository orderJpaRepository,
            ProductJpaRepository productJpaRepository,
            OrderItemJpaRepository orderItemJpaRepository) {
        this.orderRepository = new OrderRepositoryImpl(
                orderJpaRepository,
                orderItemJpaRepository,
                productJpaRepository
        );
    }

    Order givenTestData() {
        Product product = Product.builder()
                .id(1L)
                .name("바지")
                .manufacturer("유니클로")
                .price(10000)
                .build();
        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .qty(3)
                .price(product.getPrice())
                .build();
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        DeliveryInfo deliveryInfo = DeliveryInfo.builder()
                .address("경기도")
                .detailAddress("1201호")
                .receiverName("박상혁")
                .receiverPhoneNumber("010-1234-5678")
                .build();
        return new Order(orderItems, deliveryInfo, OrderState.PAYMENT_WAITING);
    }

    @Test
    void spring_data_jpa가_제공하는_save메서드는_id값을_제공한다() {
        // given
        Order order = givenTestData();

        // when
        Order savedOrder = orderRepository.create(order);

        // then
        assertThat(savedOrder.getId()).isEqualTo(1);
        System.out.println(savedOrder.getId());
    }
}
