package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.Car;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {


    @Query("SELECT c FROM Car c WHERE c.make = :make AND c.model = :model AND c.kilometers = :kilometers")
    Optional<Car> getCarByMakeAndModelAndKilometers(@Param("make") String make,
                                                    @Param("model") String model,
                                                    @Param("kilometers")int kilometers);

    @Query("SELECT c FROM Car c ORDER BY size(c.pictures) DESC, lower(c.make) ")
    Set<Car> getAllByPicturesCountDescAndByModelAlphabetically();


}
