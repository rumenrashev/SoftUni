package cardealder.service.impl;

import cardealder.domain.dto.out.task06.SaleDto;
import cardealder.domain.dto.out.task06.SalesDto;
import cardealder.domain.entity.Part;
import cardealder.domain.entity.Sale;
import cardealder.domain.repository.*;
import cardealder.service.api.SaleService;
import cardealder.util.api.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static cardealder.constants.FilePaths.*;

@Service
public class SaleServiceImpl extends BaseService implements SaleService {

    public SaleServiceImpl(ModelMapper modelMapper, XmlParser xmlParser, CarRepository carRepository, PartRepository partRepository, CustomerRepository customerRepository, SaleRepository saleRepository, SupplierRepository supplierRepository) {
        super(modelMapper, xmlParser, carRepository, partRepository, customerRepository, saleRepository, supplierRepository);
    }

    @Transactional
    @Override
    public void seed() {
        Random random = new Random();
        for (int i = 0; i < 20 ; i++) {
            Sale sale = new Sale();
            sale.setCustomer(customerRepository.getRandomCustomer());
            sale.setCar(carRepository.getRandomCar());
            sale.setDiscount(random.nextInt(6) * 10);
            saleRepository.save(sale);
        }
    }

    @Override
    public void getAllSales() throws JAXBException {
        SalesDto salesDto = new SalesDto();
        List<Sale> sales = this.saleRepository.findAll();
        List<SaleDto> saleDtos = new ArrayList<>();
        for (Sale sale : sales) {
            SaleDto saleDto = this.modelMapper.map(sale, SaleDto.class);
            saleDto.setDiscount((double)sale.getDiscount() / 100);
            BigDecimal price = BigDecimal.ZERO;
            List<Part> parts = sale.getCar().getParts();
            for (Part part : parts) {
                price = price.add(part.getPrice());
            }
            BigDecimal priceWithDiscount =
                    price.subtract(
                            (price.multiply(new BigDecimal(sale.getDiscount())))
                            .divide(new BigDecimal(100), RoundingMode.CEILING));
            saleDto.setPriceWithDiscount(priceWithDiscount);
            saleDto.setPrice(price);
            saleDtos.add(saleDto);
        }
        salesDto.setSales(saleDtos);
        Class<? extends SalesDto> aClass = salesDto.getClass();

        xmlParser.toXML(SalesDto.class,salesDto,SALES_DISCOUNTS);
    }
}
