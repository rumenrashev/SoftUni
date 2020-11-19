package cardealer.services.interfaces;

import java.io.IOException;

public interface CarService {

    void seedCars() throws IOException;

    void getToyotaCats() throws IOException;

    void getCarsWithParts() throws IOException;

}
