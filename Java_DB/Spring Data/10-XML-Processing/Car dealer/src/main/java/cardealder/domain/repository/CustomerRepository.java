package cardealder.domain.repository;

import cardealder.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(
            value = "SELECT * FROM customers ORDER BY RAND() LIMIT 1",
            nativeQuery = true
    )
    Customer getRandomCustomer();

    @Query("SELECT c FROM Customer c ORDER BY c.birthDate , c.youngDriver")
    Set<Customer> getAllByOrderByBirthDateAndIsYoungDrive();

    @Query("SELECT c FROM Customer c WHERE size( c.sales) > 0")
    List<Customer> getAllWithSale();

}
