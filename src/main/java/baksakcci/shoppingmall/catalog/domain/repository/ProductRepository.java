package baksakcci.shoppingmall.catalog.domain.repository;

import baksakcci.shoppingmall.catalog.domain.entity.Product;

public interface ProductRepository {

    long save(Product product);

    Product findById(Long id);
}
