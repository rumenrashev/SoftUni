package cardealer.services.interfaces;

import java.io.IOException;

public interface SupplierService {

    void seedSuppliers() throws IOException;

    void getLocalSuppliers() throws IOException;

}
