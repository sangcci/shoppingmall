package baksakcci.shoppingmall.order.domain.entity;

public class DeliveryInfo {

    private String receiverName;
    private String receiverPhoneNumber;
    private String deliveryAddress;
    private String deliveryDetailAddress;

    // constructor
    public DeliveryInfo(String receiverName, String receiverPhoneNumber, String deliveryAddress,
            String deliveryDetailAddress) {
        this.receiverName = receiverName;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDetailAddress = deliveryDetailAddress;
    }
}
