package cardealer.services.interfaces;

import java.io.IOException;

public interface CustomerService {

    void seedCustomers() throws IOException;

    void orderedCustomers() throws IOException;

    void customersWithTotalSpent() throws IOException;

}
