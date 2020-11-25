package hiberspring.repository;

import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query("SELECT e FROM Employee e WHERE e.card.number = :number" )
    Optional<Employee> getByCard(String number);

    @Query("SELECT e FROM Employee e WHERE size( e.branch.products) > 0" +
            "ORDER BY LOWER(CONCAT(e.firstName ,' ', e.lastName)) , LENGTH(e.position) DESC")
    Set<Employee> getAllBy();

}
