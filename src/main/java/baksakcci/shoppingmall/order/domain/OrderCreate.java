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
    @NotBlank
    private String address;
    @NotBlank
    private String detailAddress;
    @NotBlank
    private String receiverName;
    @NotBlank
    private String receiverPhoneNumber;
    private Long ordererId;

    @Builder
    @Getter
    public static class OrderItemCreate {

        @NotNull
        @Min(1)
        private Long productId;
        @NotNull
        @Min(1)
        private int qty;
    }
}