package cardealder.service.impl;

import cardealder.domain.repository.*;
import cardealder.util.api.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BaseService {

    protected final ModelMapper modelMapper;
    protected final XmlParser xmlParser;
    protected final CarRepository carRepository;
    protected final PartRepository partRepository;
    protected final CustomerRepository customerRepository;
    protected final SaleRepository saleRepository;
    protected final SupplierRepository supplierRepository;

    public BaseService(ModelMapper modelMapper, XmlParser xmlParser, CarRepository carRepository,
                       PartRepository partRepository, CustomerRepository customerRepository,
                       SaleRepository saleRepository, SupplierRepository supplierRepository) {
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.supplierRepository = supplierRepository;
    }
}
