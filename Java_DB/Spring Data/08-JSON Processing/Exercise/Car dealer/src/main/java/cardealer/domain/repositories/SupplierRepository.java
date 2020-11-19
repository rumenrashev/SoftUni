package cardealer.domain.repositories;

import cardealer.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {

    @Query("SELECT s FROM Supplier s WHERE s.importer = false ")
    List<Supplier> getAllByIsImporter();

}
