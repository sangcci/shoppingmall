package baksakcci.shoppingmall.order.application.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderCreate {

    private List<OrderItemCreate> orderItemCreates;
    private String address;
    private String detailAddress;
    private String receiverName;
    private String receiverPhoneNumber;
    private Long ordererId;

}