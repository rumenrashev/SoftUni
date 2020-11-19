package cardealer.services.impl;

import cardealer.domain.dtos.out.CarExportDto;
import cardealer.domain.dtos.out.CarSaleExportDto;
import cardealer.domain.dtos.out.SaleDto;
import cardealer.domain.entities.Customer;
import cardealer.domain.entities.Part;
import cardealer.domain.entities.Sale;
import cardealer.domain.repositories.CarRepository;
import cardealer.domain.repositories.CustomerRepository;
import cardealer.domain.repositories.SaleRepository;
import cardealer.services.interfaces.SaleService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static cardealer.constants.Paths.*;
@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, ModelMapper modelMapper, Gson gson) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Transactional
    @Override
    public void seedSales() {
        for (int i = 0; i <  6 ; i++) {
            Customer customer = this.customerRepository.getRandomEntity();
            for (int j = 0; j < i ; j++) {
                Sale sale = new Sale();
                sale.setDiscount(i * 10);
                sale.setCar(this.carRepository.getRandomEntity());
                sale.setCustomer(customer);
                this.saleRepository.save(sale);
            }
        }
    }

    @Override
    public void getSalesWithCarCustomerPrice() throws IOException {
        List<Sale> sales = this.saleRepository.findAll();
        List<SaleDto> saleDtos = new ArrayList<>();
        for (Sale sale : sales) {
            SaleDto saleDto = new SaleDto();
            saleDto.setCar(this.modelMapper.map(sale.getCar(), CarSaleExportDto.class));
            saleDto.setCustomerName(sale.getCustomer().getName());
            saleDto.setDiscount(sale.getDiscount());
            BigDecimal priceWithoutDiscount = BigDecimal.ZERO;
            for (Part part : sale.getCar().getParts()) {
                priceWithoutDiscount = priceWithoutDiscount.add(part.getPrice());
            }
            saleDto.setPriceWithoutDiscount(priceWithoutDiscount);
            BigDecimal price = priceWithoutDiscount;
            if (sale.getDiscount() != 0){
               price = price.divide(new BigDecimal(1 + (double)sale.getDiscount() / 100),RoundingMode.CEILING);
            }
            saleDto.setPrice(price);
            saleDtos.add(saleDto);
        }
        FileWriter writer = new FileWriter(SALES_DISCOUNTS);
        this.gson.toJson(saleDtos,writer);
        writer.flush();
        writer.close();
    }
}
