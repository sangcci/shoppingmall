package baksakcci.shoppingmall.order.mock;

import baksakcci.shoppingmall.catalog.domain.entity.Product;
import baksakcci.shoppingmall.catalog.domain.repository.ProductRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ProductFakeRepository implements ProductRepository {

    private static final Map<Long, Product> products = new HashMap<>();

    @Override
    public long save(Product product) {
        products.put(product.getId(), product);
        return product.getId();
    }

    @Override
    public Product findById(Long id) {
        Product product = products.get(id);
        if (product == null) throw new NoSuchElementException("No product found with id: " + id);
        return product;
    }
}
