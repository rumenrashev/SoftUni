package cardealder.service.api;

import javax.xml.bind.JAXBException;

public interface SupplierService extends Service  {

    void getLocalSuppliers() throws JAXBException;
}
