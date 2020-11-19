package cardealer.init;

import cardealer.services.interfaces.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements CommandLineRunner {

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    public AppInitializer(SupplierService supplierService, PartService partService, CarService carService,
                          CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.supplierService.seedSuppliers();
        this.partService.seedParts();
        this.carService.seedCars();
        this.customerService.seedCustomers();
        this.saleService.seedSales();
        this.customerService.orderedCustomers();
        this.carService.getToyotaCats();
        this.supplierService.getLocalSuppliers();
        this.carService.getCarsWithParts();
        this.customerService.customersWithTotalSpent();
        this.saleService.getSalesWithCarCustomerPrice();

        System.exit(0);
    }
}
