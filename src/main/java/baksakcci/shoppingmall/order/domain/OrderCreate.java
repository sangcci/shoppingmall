package baksakcci.shoppingmall.order.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderCreate {

    @Valid
    private List<OrderItemCreate> orderItemCreates;
    @NotBlank(message = "주소가 없습니다. 주소를 입력해주세요.")
    private String address;
    @NotBlank(message = "상세주소가 없습니다. 상세주소를 입력해주세요.")
    private String detailAddress;
    @NotBlank(message = "받는사람 정보가 없습니다. 받는사람 정보를 입력해주세요.")
    private String receiverName;
    @NotBlank(message = "받는사람 연락처 정보가 없습니다. 받는사람 연락처 정보를 입력해주세요.")
    private String receiverPhoneNumber;
    private Long ordererId;

    @Builder
    @Getter
    public static class OrderItemCreate {

        @NotNull(message = "주문 상품 id값이 없습니다. 주문하고자 하는 상품 id값을 입력해주세요.")
        @Min(value = 1, message = "주문 상품 id값은 0이 될 수 없습니다. 1 이상의 값을 입력해주세요")
        private Long productId;
        @NotNull(message = "주문 상품 수량이 없습니다. 주문 상품 수량을 입력해주세요.")
        @Min(value = 1, message = "주문 상품 수량은 0이 될 수 없습니다. 1 이상의 값을 입력해주세요")
        private int qty;
    }
}