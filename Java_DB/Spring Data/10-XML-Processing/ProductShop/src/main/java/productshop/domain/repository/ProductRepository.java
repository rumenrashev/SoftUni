package productshop.domain.repository;

import org.springframework.data.jpa.repository.Query;
import productshop.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getAllByBuyerIsNullAndPriceBetween(BigDecimal price, BigDecimal price2);

    @Query(value = "SELECT *  FROM products ORDER BY RAND() LIMIT 1",nativeQuery = true)
    Product getRandomProduct();
}
