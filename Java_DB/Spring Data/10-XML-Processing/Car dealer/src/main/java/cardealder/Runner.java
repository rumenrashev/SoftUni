package cardealder;

import cardealder.service.api.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    public Runner(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.supplierService.seed();
        this.partService.seed();
        this.carService.seed();
        this.customerService.seed();
        this.saleService.seed();
        this.customerService.getOrderedCustomers();
        this.carService.getToyotaCars();
        this.supplierService.getLocalSuppliers();
        this.carService.getAllCarsAndParts();
        this.customerService.getCustomersTotalSales();
        this.saleService.getAllSales();

        System.exit(0);
    }
}
