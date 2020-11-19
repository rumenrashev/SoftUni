package json.domain.repositories;

import json.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getAllByBuyerIsNullAndPriceBetween(BigDecimal price, BigDecimal price2);
}
