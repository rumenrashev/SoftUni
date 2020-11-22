package cardealder.service.api;

import cardealder.domain.entity.Sale;

import javax.xml.bind.JAXBException;

public interface SaleService extends Service  {

    void getAllSales() throws JAXBException;

}
