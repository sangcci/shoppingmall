package baksakcci.shoppingmall.order.fixture;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import java.time.LocalDate;

public class ProductFixtureProvider {

    public static Product 상품_생성() {
        return Product.builder()
                .id(1L)
                .name("꿀사과")
                .manufacturer("영주시")
                .price(2000)
                .date(LocalDate.now())
                .build();
    }
}
