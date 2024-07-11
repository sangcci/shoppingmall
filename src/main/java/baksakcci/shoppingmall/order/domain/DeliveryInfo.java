package baksakcci.shoppingmall.order.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeliveryInfo {

    private final String address;
    private final String detailAddress;
    private final String receiverName;
    private final String receiverPhoneNumber;

    // constructor
    @Builder
    public DeliveryInfo(String address, String detailAddress, String receiverName, String receiverPhoneNumber) {
        this.address = address;
        this.detailAddress = detailAddress;
        this.receiverName = receiverName;
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    // validation
}
