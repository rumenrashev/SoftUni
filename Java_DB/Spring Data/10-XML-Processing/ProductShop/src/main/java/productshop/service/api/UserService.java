package productshop.service.api;

import javax.xml.bind.JAXBException;

public interface UserService {

    void seedUsers() throws JAXBException;

    void getUsersSoldProducts() throws JAXBException;

    void getUsersAndProducts() throws JAXBException;

}
