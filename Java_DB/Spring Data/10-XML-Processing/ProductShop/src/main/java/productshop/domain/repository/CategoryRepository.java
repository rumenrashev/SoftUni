package productshop.domain.repository;

import productshop.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c ORDER BY c.products.size DESC")
    List<Category> findAllOrderByProductsCount();

    @Query(value = "SELECT * FROM categories ORDER BY RAND() LIMIT 1",nativeQuery = true)
    Category getRandomCategory();

    @Query("SELECT c FROM Category c ORDER BY size(c.products) DESC")
    Set<Category> getAllOrderByProductsCount();

}
