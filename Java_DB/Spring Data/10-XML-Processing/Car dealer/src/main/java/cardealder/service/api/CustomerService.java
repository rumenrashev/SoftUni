package cardealder.service.api;

import javax.xml.bind.JAXBException;

public interface CustomerService extends Service  {

    void getOrderedCustomers() throws JAXBException;

    void getCustomersTotalSales() throws JAXBException;

}
