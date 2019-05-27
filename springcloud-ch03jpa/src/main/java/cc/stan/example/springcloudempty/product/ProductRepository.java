package cc.stan.example.springcloudempty.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
    Product findBySku(String sku);

    List<Product> findByName(String name);
}
