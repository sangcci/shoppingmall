package baksakcci.shoppingmall.catalog.infra;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import baksakcci.shoppingmall.catalog.domain.repository.ProductRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public long save(Product product) {
        ProductEntity productEntity = ProductEntity.from(product);
        productJpaRepository.save(productEntity);
        return productEntity.getId();
    }

    @Override
    public Product findById(Long id) {
        ProductEntity productEntity = productJpaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(id + "번 상품이 존재하지 않습니다."));
        return productEntity.toModel();
    }
}
