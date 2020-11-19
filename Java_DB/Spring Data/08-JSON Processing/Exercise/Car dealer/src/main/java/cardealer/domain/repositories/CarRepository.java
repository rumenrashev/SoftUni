package cardealer.domain.repositories;

import cardealer.domain.entities.Car;
import cardealer.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    @Query(value = "SELECT * FROM cars ORDER BY RAND () LIMIT 1", nativeQuery = true)
    Car getRandomEntity();

    @Query("SELECT c FROM Car c WHERE c.make = :make ORDER BY LOWER( c.model) , c.travelledDistance DESC")
    List<Car> getAllByMakeOrderByModelAlphabeticallyAndTravelledDistanceDesc(String make);


}
