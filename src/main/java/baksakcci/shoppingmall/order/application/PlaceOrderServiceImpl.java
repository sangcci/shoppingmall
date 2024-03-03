package baksakcci.shoppingmall.order.application;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import baksakcci.shoppingmall.catalog.domain.repository.ProductRepository;
import baksakcci.shoppingmall.order.application.dto.OrderCreate;
import baksakcci.shoppingmall.order.application.dto.OrderItemCreate;
import baksakcci.shoppingmall.order.application.port.PlaceOrderService;
import baksakcci.shoppingmall.order.domain.entity.Order;
import baksakcci.shoppingmall.order.domain.entity.OrderItem;
import baksakcci.shoppingmall.order.application.port.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlaceOrderServiceImpl implements PlaceOrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order placeOrder(OrderCreate orderCreate) {
        // 회원 정보 확인(주문자)

        // 상품 정보 확인 (굳이 검증까진 하지 않음. 이미 product 도메인에서 검증되어서 만들어져서 저장되었기 때문)
        List<OrderItemCreate> orderItemCreates = orderCreate.getOrderItemCreates();
        if (orderItemCreates.isEmpty()) throw new NullPointerException();

        List<OrderItem> orderItems = orderItemCreates.stream().map(each -> {
            Product product = productRepository.findById(each.getProductId());
            // (상품 검증 로직들...) -> 얘네들은 Order 애그리거트 내부의 책임이므로 여기서 구현할게 아님. 밑이나 Order 내부에서 구현
            return OrderItem.builder()
                    .productId(product.getId())
                    .qty(each.getQty())
                    .price(product.getPrice())
                    .build();
        }).toList();

        // 주문 생성 및 저장
        Order order = Order.from(orderCreate, orderItems);
        orderRepository.create(order);

        return order;
    }

}
