package cardealer.services.impl;

import cardealer.domain.dtos.in.CustomerInDto;
import cardealer.domain.dtos.out.CustomerExportDto;
import cardealer.domain.dtos.out.OrderedCustomerDto;
import cardealer.domain.entities.Car;
import cardealer.domain.entities.Customer;
import cardealer.domain.entities.Part;
import cardealer.domain.entities.Sale;
import cardealer.domain.repositories.CustomerRepository;
import cardealer.services.interfaces.CustomerService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cardealer.constants.Paths.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, Gson gson, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public void seedCustomers() throws IOException {
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss");
        String content = String.join(EMPTY_DELIMITER, Files.readAllLines(Path.of(CUSTOMERS_IN_PATH)));
        CustomerInDto[] customerInDtos = gson.fromJson(content, CustomerInDto[].class);
        for (CustomerInDto customerInDto : customerInDtos) {
            Customer customer = new Customer();
            customer.setName(customerInDto.getName());
            customer.setBirthDate(LocalDate.parse(customerInDto.getBirthDate(), dft));
            customer.setYoungDriver(customerInDto.getYoungDriver());
            this.customerRepository.save(customer);
        }
    }

    @Override
    public void orderedCustomers() throws IOException {
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Customer> orderedCustomers = this.customerRepository.getAllByrOrderByBirthDateAndYoungDrive();
        OrderedCustomerDto[] orderedCustomerDtos = orderedCustomers
                .stream()
                .map(c -> this.modelMapper.map(c, OrderedCustomerDto.class))
                .toArray(OrderedCustomerDto[]::new);
        FileWriter writer = new FileWriter(ORDERED_CUSTOMERS);
        String out = this.gson.toJson(orderedCustomerDtos);
        this.gson.toJson(orderedCustomerDtos, writer);
        writer.flush();
        writer.close();
    }

    @Override
    public void customersWithTotalSpent() throws IOException {
        List<Customer> customers = this.customerRepository.getAllCustomersWithTotalSales();
        List<CustomerExportDto> dtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerExportDto dto = new CustomerExportDto();
            dto.setFullName(customer.getName());
            dto.setBoughtCars(customer.getSales().size());
            BigDecimal totalSpent = BigDecimal.ZERO;
            for (Sale sale  : customer.getSales()) {
                for (Part part : sale.getCar().getParts()) {
                    totalSpent = totalSpent.add(part.getPrice());
                }
            }
            dto.setMoneySpent(totalSpent);
            dtos.add(dto);
        }

        dtos = dtos.stream()
                .sorted((c1,c2)->{
                    int compare = c2.getMoneySpent().compareTo(c1.getMoneySpent());
                    if (compare == 0){
                        return c2.getBoughtCars() - c1.getBoughtCars();
                    }
                    return compare;
                }).collect(Collectors.toList());

        FileWriter writer = new FileWriter(CUSTOMERS_TOTAL_SALES);
        this.gson.toJson(dtos, writer);
        writer.flush();
        writer.close();
    }
}
