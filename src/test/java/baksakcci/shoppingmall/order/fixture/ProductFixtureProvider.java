package baksakcci.shoppingmall.order.fixture;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import java.time.LocalDate;

public class ProductFixtureProvider {

    public static Product 상품_꿀사과_생성() {
        return Product.builder()
                .id(1L)
                .name("꿀사과")
                .manufacturer("영주시")
                .price(2000)
                .date(LocalDate.now())
                .build();
    }

    public static Product 상품_안창살_생성() {
        return Product.builder()
                .id(2L)
                .name("안창살")
                .manufacturer("영주한우")
                .price(65000)
                .date(LocalDate.now())
                .build();
    }
}
