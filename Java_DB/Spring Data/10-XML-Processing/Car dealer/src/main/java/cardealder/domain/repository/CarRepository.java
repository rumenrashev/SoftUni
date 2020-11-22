package cardealder.domain.repository;

import cardealder.domain.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    @Query(
            value = "SELECT * FROM cars ORDER BY RAND() LIMIT 1",
            nativeQuery = true
    )
    Car getRandomCar();

    @Query("SELECT c FROM Car c WHERE c.make = :make ORDER BY lower(c.model) , c.travelledDistance DESC ")
    Set<Car> getAllByModelOrderByModelAndTravelledDistanceDesc(String make);

}
