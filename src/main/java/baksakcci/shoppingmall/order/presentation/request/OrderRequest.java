package baksakcci.shoppingmall.order.presentation.request;

import baksakcci.shoppingmall.order.application.dto.OrderCreate;
import baksakcci.shoppingmall.order.application.dto.OrderCreate.OrderItemCreate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderRequest {

    private List<OrderItemRequest> orderItemRequests;
    private final String address;
    private final String detailAddress;
    private String receiverName;
    private String receiverPhoneNumber;

    public static OrderCreate toDto(Long userId, OrderRequest orderRequest) {
        List<OrderItemCreate> orderItemCreateDtos = orderRequest.orderItemRequests.stream()
                .map(each -> OrderCreate.OrderItemCreate.builder()
                        .productId(each.getProductId())
                        .qty(each.getQty())
                        .build())
                .toList();
        return OrderCreate.builder()
                .ordererId(userId)
                .orderItemCreates(orderItemCreateDtos)
                .receiverName(orderRequest.receiverName)
                .receiverPhoneNumber(orderRequest.receiverPhoneNumber)
                .address(orderRequest.address)
                .detailAddress(orderRequest.detailAddress)
                .build();

    }

    @AllArgsConstructor
    @Getter
    @Builder
    public static class OrderItemRequest {

        private int qty;
        private long productId;
    }
}
