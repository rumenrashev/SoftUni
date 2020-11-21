package productshop.domain.repository;

import productshop.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE size(u.soldProducts) > 0")
    Set<User> getAllWithAtLeastOneItemBought();

    @Query("" +
            "SELECT u FROM User AS u " +
            "JOIN Product AS p ON p.seller.id = u.id " +
            "WHERE p.buyer IS NOT NULL " +
            "GROUP BY u.id " +
            "ORDER BY u.soldProducts.size DESC , u.lastName")
    Set<User> findAllWithAtLeastOneProductSold();


    @Query("" +
            "SELECT u FROM User AS u " +
            "JOIN Product AS p ON p.seller.id = u.id " +
            "WHERE p.buyer IS NOT NULL " +
            "GROUP BY u.id " +
            "ORDER BY u.lastName, u.firstName")
    Set<User> getAllWithAtLeastOneSoldProduct();

    @Query(value = "SELECT *  FROM users ORDER BY RAND() LIMIT 1",nativeQuery = true)
    User getRandomUser();

    @Query("SELECT u FROM User u WHERE size(u.soldProducts) > 0 ORDER BY u.lastName, u.firstName")
    Set<User> getAllWithAtLeastOneProductSoldOrderByLastNameAndFirstName();

    @Query("SELECT u FROM User u WHERE size(u.soldProducts) > 0 ORDER BY size(u.soldProducts) DESC,u.lastName")
    Set<User> getAllWithAtLeastOneProductSoldOrderBySoldProductsCountDescAndLastName();
}
