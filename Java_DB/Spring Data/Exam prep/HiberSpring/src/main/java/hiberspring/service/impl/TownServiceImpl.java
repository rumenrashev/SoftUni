package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.json.TownJsonImportDto;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

import static hiberspring.common.Constants.*;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, FileUtil fileUtil, ModelMapper modelMapper, Gson gson,
                           ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return this.fileUtil.readFile(TOWNS_PATH);
    }

    @Override
    public String importTowns(String townsFileContent) throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(this.gson.fromJson(readTownsJsonFile(),TownJsonImportDto[].class))
                .forEach(tdto-> {
                    if (validationUtil.isValid(tdto)){
                        this.townRepository.saveAndFlush(this.modelMapper.map(tdto, Town.class));
                        sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,TOWN,tdto.getName()));
                    }else {
                        sb.append(INCORRECT_DATA_MESSAGE);
                    }
                    sb.append(System.lineSeparator());
                });
        return sb.toString();
    }
}
