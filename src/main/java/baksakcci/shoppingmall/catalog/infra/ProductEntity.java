package baksakcci.shoppingmall.catalog.infra;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String manufacturer;
    private int price;
    private LocalDate date;

    public static ProductEntity from(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.id = product.getId();
        productEntity.name = product.getName();
        productEntity.manufacturer = product.getManufacturer();
        productEntity.price = product.getPrice();
        productEntity.date = product.getDate();
        return productEntity;
    }

    public Product toModel() {
        return Product.builder()
                .id(id)
                .name(name)
                .manufacturer(manufacturer)
                .price(price)
                .date(date)
                .build();
    }
}
