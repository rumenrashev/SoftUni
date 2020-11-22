package cardealder.domain.repository;

import cardealder.domain.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(
            value = "SELECT * FROM suppliers ORDER BY RAND() LIMIT 1"
            , nativeQuery = true)
    Supplier getRandomSupplier();

    @Query("SELECT s FROM Supplier s WHERE s.importer = false ")
    Set<Supplier> getLocalSuppliers();

}
