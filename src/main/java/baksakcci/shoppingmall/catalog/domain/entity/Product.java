package baksakcci.shoppingmall.catalog.domain.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Product {

    private long id;
    private String name;
    private String manufacturer;
    private int price;
    private LocalDate date;

    public Product(long id, String name, String manufacturer, int price) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.date = LocalDate.now();
    }

}