package baksakcci.shoppingmall.catalog.domain.repository;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import java.util.Optional;

public interface ProductRepository {

    void save(Product product);

    Product findById(Long id);
}
