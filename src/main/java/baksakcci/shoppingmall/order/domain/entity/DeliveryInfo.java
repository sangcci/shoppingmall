package baksakcci.shoppingmall.order.domain.entity;

public class DeliveryInfo {

    private Receiver receiver;
    private Address address;

    // constructor
    public DeliveryInfo(Receiver receiver, Address address) {
        this.receiver = receiver;
        this.address = address;
    }
}
