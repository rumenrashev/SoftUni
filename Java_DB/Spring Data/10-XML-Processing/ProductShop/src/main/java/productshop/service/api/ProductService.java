package productshop.service.api;

import javax.xml.bind.JAXBException;

public interface ProductService {

    void seedProducts() throws JAXBException;

    void getProductInRangeWithoutBuyer() throws JAXBException;

}
