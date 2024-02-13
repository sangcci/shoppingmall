package baksakcci.shoppingmall.order.domain.entity;

import java.util.Objects;

public class Address {
    private final String deliveryAddress;
    private final String deliveryDetailAddress;

    // constructor
    public Address(String deliveryAddress, String deliveryDetailAddress) {
        this.deliveryAddress = deliveryAddress;
        this.deliveryDetailAddress = deliveryDetailAddress;
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
        Address address = (Address) o;
        return Objects.equals(deliveryAddress, address.deliveryAddress) && Objects.equals(
                deliveryDetailAddress, address.deliveryDetailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryAddress, deliveryDetailAddress);
    }
}
