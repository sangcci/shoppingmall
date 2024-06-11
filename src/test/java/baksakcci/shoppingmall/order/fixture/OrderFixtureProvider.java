package baksakcci.shoppingmall.order.fixture;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import baksakcci.shoppingmall.order.domain.DeliveryInfo;
import baksakcci.shoppingmall.order.domain.Order;
import baksakcci.shoppingmall.order.domain.OrderCreate;
import baksakcci.shoppingmall.order.domain.OrderCreate.OrderItemCreate;
import baksakcci.shoppingmall.order.domain.OrderData;
import baksakcci.shoppingmall.order.domain.OrderData.OrderItemData;
import baksakcci.shoppingmall.order.domain.OrderItem;
import baksakcci.shoppingmall.order.domain.OrderState;
import baksakcci.shoppingmall.order.mock.ClockHolder;
import java.util.ArrayList;
import java.util.List;

public class OrderFixtureProvider {

    public static Order 주문_생성(Product productA, Product productB) {
        return new Order(주문_상품_생성(productA, productB), 배송지_생성(), OrderState.PAYMENT_WAITING);
    }

    public static Order 주문_생성_배송_중_상태(Product productA, Product productB) {
        return new Order(주문_상품_생성(productA, productB), 배송지_생성(), OrderState.DELIVERING);
    }

    private static List<OrderItem> 주문_상품_생성(Product productA, Product productB) {
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(OrderItem.builder()
                .product(productA)
                .qty(2)
                .price(productA.getPrice())
                .build());
        orderItems.add(OrderItem.builder()
                .product(productB)
                .qty(1)
                .price(productB.getPrice())
                .build());
        return orderItems;
    }

    private static DeliveryInfo 배송지_생성() {
        return DeliveryInfo.builder()
                .address("경기도")
                .detailAddress("땡땡빌딩 101호")
                .receiverName("박상혁")
                .receiverPhoneNumber("010-1234-5678")
                .build();
    }

    public static OrderData 주문_상세_정보_생성() {
        List<OrderItemData> orderItemDatas = new ArrayList<>();
        orderItemDatas.add(OrderItemData.builder()
                .productId(1L)
                .name("꿀사과")
                .qty(2)
                .price(2000)
                .build());
        orderItemDatas.add(OrderItemData.builder()
                .productId(2L)
                .name("안창살")
                .qty(1)
                .price(65000)
                .build());
        return OrderData.builder()
                .id(1L)
                .orderState(OrderState.PAYMENT_WAITING)
                .orderAt(new ClockHolder().getCurrentTime())
                .orderItemDatas(orderItemDatas)
                .build();
    }

    public static OrderCreate 주문서_생성() {
        ArrayList<OrderItemCreate> items = new ArrayList<>();
        items.add(OrderItemCreate.builder()
                .productId(1L)
                .qty(2)
                .build());
        items.add(OrderItemCreate.builder()
                .productId(2L)
                .qty(1)
                .build());
        return OrderCreate.builder()
                .orderItemCreates(items)
                .address("경기도")
                .detailAddress("땡땡빌딩 101호")
                .receiverName("홍길동")
                .receiverPhoneNumber("010-1234-5678")
                .build();
    }
}
