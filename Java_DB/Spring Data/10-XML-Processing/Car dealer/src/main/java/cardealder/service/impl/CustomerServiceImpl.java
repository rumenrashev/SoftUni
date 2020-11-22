package cardealder.service.impl;

import cardealder.domain.dto.in.CustomerImportDto;
import cardealder.domain.dto.in.CustomersImportDto;
import cardealder.domain.dto.out.task01.OrderedCustomerDto;
import cardealder.domain.dto.out.task01.OrderedCustomersDto;
import cardealder.domain.dto.out.task05.CustomerTotalSalesDto;
import cardealder.domain.dto.out.task05.CustomersTotalSalesDto;
import cardealder.domain.entity.Car;
import cardealder.domain.entity.Customer;
import cardealder.domain.entity.Part;
import cardealder.domain.entity.Sale;
import cardealder.domain.repository.*;
import cardealder.service.api.CustomerService;
import cardealder.util.api.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cardealder.constants.FilePaths.*;

@Service
public class CustomerServiceImpl extends BaseService implements CustomerService {


    public CustomerServiceImpl(ModelMapper modelMapper, XmlParser xmlParser, CarRepository carRepository, PartRepository partRepository, CustomerRepository customerRepository, SaleRepository saleRepository, SupplierRepository supplierRepository) {
        super(modelMapper, xmlParser, carRepository, partRepository, customerRepository, saleRepository, supplierRepository);
    }

    @Override
    public void seed() throws JAXBException {
        CustomersImportDto customersImportDto = xmlParser.fromXML(CustomersImportDto.class, CUSTOMERS);

        for (CustomerImportDto customerDto : customersImportDto.getCustomers()) {
            Customer customer = new Customer();
            customer.setName(customerDto.getName());
            customer.setBirthDate(LocalDateTime.parse(customerDto.getBirthDate()));
            customer.setYoungDriver(customerDto.getYoungDriver());
            this.customerRepository.save(customer);
        }
    }

    @Override
    public void getOrderedCustomers() throws JAXBException {
        OrderedCustomersDto orderedCustomersDto = new OrderedCustomersDto();
        Set<OrderedCustomerDto> dtos = this.customerRepository
                .getAllByOrderByBirthDateAndIsYoungDrive()
                .stream()
                .map(c -> modelMapper.map(c, OrderedCustomerDto.class))
                .collect(Collectors.toSet());
        orderedCustomersDto.setCustomers(dtos);
        xmlParser.toXML(OrderedCustomersDto.class,orderedCustomersDto,ORDERED_CUSTOMERS);
    }

    @Override
    public void getCustomersTotalSales() throws JAXBException {
        CustomersTotalSalesDto customersTotalSalesDto = new CustomersTotalSalesDto();
        List<CustomerTotalSalesDto> dtos = new ArrayList<>();
        List<Customer> customers = this.customerRepository.getAllWithSale();
        for (Customer customer : customers) {
            CustomerTotalSalesDto customerTotalSalesDto = new CustomerTotalSalesDto();
            customerTotalSalesDto.setFullName(customer.getName());
            List<Sale> sales = customer.getSales();
            customerTotalSalesDto.setBoughtCars(sales.size());
            BigDecimal spentMoney = BigDecimal.ZERO;
            for (Sale sale : sales) {
                Car car = sale.getCar();
                List<Part> parts = car.getParts();
                for (Part part : parts) {
                    spentMoney = spentMoney.add(part.getPrice());
                }
            }
            customerTotalSalesDto.setSpentMoney(spentMoney);
            dtos.add(customerTotalSalesDto);
        }
        dtos = dtos
                .stream()
                .sorted((c1,c2)->{
                    int compare = c2.getBoughtCars() - c1.getBoughtCars();
                    if (compare == 0){
                        return c2.getSpentMoney().compareTo(c1.getSpentMoney());
                    }
                    return compare;
                })
                .collect(Collectors.toList());;
        customersTotalSalesDto.setCustomers(dtos);
        xmlParser.toXML(CustomersTotalSalesDto.class,customersTotalSalesDto,CUSTOMERS_TOTAL_SALES);
    }
}
