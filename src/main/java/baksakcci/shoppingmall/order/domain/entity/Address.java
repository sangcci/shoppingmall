package baksakcci.shoppingmall.order.domain.entity;

public class Address {
    private String deliveryAddress;
    private String deliveryDetailAddress;

    // constructor
    public Address(String deliveryAddress, String deliveryDetailAddress) {
        this.deliveryAddress = deliveryAddress;
        this.deliveryDetailAddress = deliveryDetailAddress;
    }
}
