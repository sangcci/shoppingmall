package baksakcci.shoppingmall.order.domain.entity;

import java.util.Objects;

public class Receiver {
    private final String receiverName;
    private final String receiverPhoneNumber;

    // constructor
    public Receiver(String receiverName, String receiverPhoneNumber) {
        this.receiverName = receiverName;
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    // immutable
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Receiver receiver = (Receiver) o;
        return Objects.equals(receiverName, receiver.receiverName) && Objects.equals(
                receiverPhoneNumber, receiver.receiverPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiverName, receiverPhoneNumber);
    }
}
