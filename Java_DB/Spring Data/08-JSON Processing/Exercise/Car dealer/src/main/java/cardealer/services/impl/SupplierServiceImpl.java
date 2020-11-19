package cardealer.services.impl;

import cardealer.domain.dtos.in.SupplierInDto;
import cardealer.domain.dtos.out.LocalSupplierDto;
import cardealer.domain.entities.Supplier;
import cardealer.domain.repositories.SupplierRepository;
import cardealer.services.interfaces.SupplierService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cardealer.constants.Paths.*;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, Gson gson) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Transactional
    @Override
    public void seedSuppliers() throws IOException {
        String content = String.join(EMPTY_DELIMITER, Files.readAllLines(Path.of(SUPPLIERS_IN_PATH)));
        Arrays.stream(this.gson.fromJson(content, SupplierInDto[].class))
                .map(s -> this.modelMapper.map(s, Supplier.class))
                .forEach(this.supplierRepository::save);

    }

    @Override
    public void getLocalSuppliers() throws IOException {
        List<Supplier> suppliers = this.supplierRepository.getAllByIsImporter();
        List<LocalSupplierDto> dtos = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            LocalSupplierDto dto = modelMapper.map(supplier, LocalSupplierDto.class);
            dto.setPartsCount(supplier.getParts().size());
            dtos.add(dto);
        }
        FileWriter writer = new FileWriter(LOCAL_SUPPLIERS);
        this.gson.toJson(dtos,writer);
        writer.flush();
        writer.close();
    }
}
