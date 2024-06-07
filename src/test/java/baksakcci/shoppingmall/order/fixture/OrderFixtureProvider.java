package baksakcci.shoppingmall.order.fixture;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import baksakcci.shoppingmall.order.domain.DeliveryInfo;
import baksakcci.shoppingmall.order.domain.Order;
import baksakcci.shoppingmall.order.domain.OrderItem;
import baksakcci.shoppingmall.order.domain.OrderState;
import java.util.ArrayList;
import java.util.List;

public class OrderFixtureProvider {

    public static Order 주문_생성(List<OrderItem> orderItems, DeliveryInfo deliveryInfo) {
        return new Order(orderItems, deliveryInfo, OrderState.PAYMENT_WAITING);
    }

    public static List<OrderItem> 주문_상품_생성(Product product) {
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(OrderItem.builder()
                .product(product)
                .qty(2)
                .price(2000)
                .build());
        return orderItems;
    }

    public static DeliveryInfo 배송지_생성() {
        return DeliveryInfo.builder()
                .address("경기도")
                .detailAddress("땡땡빌딩 101호")
                .receiverName("박상혁")
                .receiverPhoneNumber("010-1234-5678")
                .build();
    }
}
