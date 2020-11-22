package cardealder.service.api;

import javax.xml.bind.JAXBException;

public interface CarService extends Service  {

    void getToyotaCars() throws JAXBException;

    void getAllCarsAndParts() throws JAXBException;

}
