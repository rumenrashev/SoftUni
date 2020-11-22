package cardealder.service.impl;

import cardealder.domain.dto.in.SuppliersImportDto;
import cardealder.domain.dto.out.task03.LocalSupplierDto;
import cardealder.domain.dto.out.task03.LocalSuppliersDto;
import cardealder.domain.entity.Supplier;
import cardealder.domain.repository.*;
import cardealder.service.api.SupplierService;
import cardealder.util.api.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

import java.util.LinkedHashSet;
import java.util.Set;

import static cardealder.constants.FilePaths.*;

@Service
public class SupplierServiceImpl extends BaseService implements SupplierService {
    public SupplierServiceImpl(ModelMapper modelMapper, XmlParser xmlParser, CarRepository carRepository,
                               PartRepository partRepository, CustomerRepository customerRepository,
                               SaleRepository saleRepository, SupplierRepository supplierRepository) {
        super(modelMapper, xmlParser, carRepository, partRepository, customerRepository, saleRepository,
                supplierRepository);
    }
    @Override
    public void seed() throws JAXBException {
        xmlParser.fromXML(SuppliersImportDto.class, SUPPLIERS)
                .getSuppliers()
                .stream()
                .map(sdto -> modelMapper.map(sdto,Supplier.class))
                .forEach(this.supplierRepository::save);
    }

    @Override
    public void getLocalSuppliers() throws JAXBException {
        Set<Supplier> suppliers = this.supplierRepository.getLocalSuppliers();
        Set<LocalSupplierDto> dtos = new LinkedHashSet<>();
        for (Supplier supplier : suppliers) {
            LocalSupplierDto supplierDto = this.modelMapper.map(supplier, LocalSupplierDto.class);
            supplierDto.setPartsCount(supplier.getParts().size());
            dtos.add(supplierDto);
        }
        LocalSuppliersDto localSuppliersDto = new LocalSuppliersDto();
        localSuppliersDto.setSuppliers(dtos);
        xmlParser.toXML(LocalSuppliersDto.class,localSuppliersDto,LOCAL_SUPPLIERS);
    }
}
