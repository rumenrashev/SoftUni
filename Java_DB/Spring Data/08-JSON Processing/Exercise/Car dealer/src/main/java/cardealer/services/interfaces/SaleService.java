package cardealer.services.interfaces;

import java.io.IOException;

public interface SaleService {

    void seedSales();

    void getSalesWithCarCustomerPrice() throws IOException;

}
