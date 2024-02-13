package baksakcci.shoppingmall.order.domain.entity;

public enum OrderState {
    // 결제 대기, 상품 준비, 배송 대기, 배송중, 배송 완료
    PAYMENT_WAITING,
    ITEM_PREPARING,
    DELIVERING,
    DELIVERY_COMPLETE,
    CANCELED;
}
