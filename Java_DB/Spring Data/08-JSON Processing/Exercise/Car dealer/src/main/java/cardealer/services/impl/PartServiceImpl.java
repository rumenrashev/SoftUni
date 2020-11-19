package cardealer.services.impl;

import cardealer.common.RandomGenerator;
import cardealer.domain.dtos.in.PartInDto;
import cardealer.domain.entities.Part;
import cardealer.domain.entities.Supplier;
import cardealer.domain.repositories.PartRepository;
import cardealer.domain.repositories.SupplierRepository;
import cardealer.services.interfaces.PartService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static cardealer.constants.Paths.*;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final RandomGenerator randomGenerator;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository,
                           ModelMapper modelMapper, Gson gson, RandomGenerator randomGenerator) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.randomGenerator = randomGenerator;
    }

    @Transactional
    @Override
    public void seedParts() throws IOException {
        String content = String.join(EMPTY_DELIMITER, Files.readAllLines(Path.of(PARTS_IN_PATH)));
        PartInDto[] partInDtos = this.gson.fromJson(content, PartInDto[].class);
        for (PartInDto partInDto : partInDtos) {
            Part part = this.modelMapper.map(partInDto, Part.class);
            Supplier supplier = getRandomSupplier();
            supplier.getParts().add(part);
            part.setSupplier(supplier);
            this.partRepository.save(part);
        }
    }

    private Supplier getRandomSupplier(){
        Long id = randomGenerator.generateId(this.supplierRepository.count());
        Optional<Supplier> supplier = this.supplierRepository.findById(id);
        return supplier.orElse(null);
    }
}
