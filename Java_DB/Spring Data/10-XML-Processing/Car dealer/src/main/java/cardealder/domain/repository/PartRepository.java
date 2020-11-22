package cardealder.domain.repository;

import cardealder.domain.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {


    @Query(
            value = "SELECT * FROM parts ORDER BY RAND() LIMIT :count",
            nativeQuery = true
    )
    List<Part> getRandomParts(int count);
    
}
