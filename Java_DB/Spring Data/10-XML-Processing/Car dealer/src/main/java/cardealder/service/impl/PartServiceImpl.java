package cardealder.service.impl;

import cardealder.domain.dto.in.PartsImportDto;
import cardealder.domain.entity.Part;
import cardealder.domain.repository.*;
import cardealder.service.api.PartService;
import cardealder.util.api.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;

import static cardealder.constants.FilePaths.*;

@Service
public class PartServiceImpl extends BaseService implements PartService {

    public PartServiceImpl(ModelMapper modelMapper, XmlParser xmlParser, CarRepository carRepository, PartRepository partRepository, CustomerRepository customerRepository, SaleRepository saleRepository, SupplierRepository supplierRepository) {
        super(modelMapper, xmlParser, carRepository, partRepository, customerRepository, saleRepository, supplierRepository);
    }

    @Override
    @Transactional
    public void seed() throws JAXBException {

        PartsImportDto partsImportDto = xmlParser.fromXML(PartsImportDto.class, PARTS);
        System.out.println();

        xmlParser.fromXML(PartsImportDto.class,PARTS)
                .getParts()
                .stream()
                .map(pdto -> modelMapper.map(pdto, Part.class))
                .forEach(p -> {
                    p.setSupplier(supplierRepository.getRandomSupplier());
                    partRepository.save(p);
                });
    }
}
