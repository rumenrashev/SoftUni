package cardealer.domain.repositories;

import cardealer.domain.dtos.out.CustomerExportDto;
import cardealer.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query(value = "SELECT * FROM customers ORDER BY RAND () LIMIT 1", nativeQuery = true)
    Customer getRandomEntity();

    @Query("SELECT c FROM Customer c ORDER BY c.birthDate , c.youngDriver DESC")
    List<Customer> getAllByrOrderByBirthDateAndYoungDrive();

    @Query("SELECT c FROM Customer c WHERE size(c.sales) > 0 ")
    List<Customer> getAllCustomersWithTotalSales();
}
