package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.json.TownJsonImportDto;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static softuni.exam.constants.GlobalConstants.*;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, Gson gson,
                           ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWNS_IMPORT_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();
        TownJsonImportDto[] townJsonImportDtos =
                this.gson.fromJson(readTownsFileContent(), TownJsonImportDto[].class);
        for (TownJsonImportDto townJsonImportDto : townJsonImportDtos) {

            Optional<Town> townByName = this.townRepository.findByName(townJsonImportDto.getName());

            if (townByName.isEmpty() && this.validationUtil.isValid(townJsonImportDto)){
                Town town = this.modelMapper.map(townJsonImportDto,Town.class);
                this.townRepository.saveAndFlush(town);
                sb.append(String.format(IMPORT_TOWN,town.getName(),town.getPopulation()));
            }else {
                sb.append(INVALID_TOWN);
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
