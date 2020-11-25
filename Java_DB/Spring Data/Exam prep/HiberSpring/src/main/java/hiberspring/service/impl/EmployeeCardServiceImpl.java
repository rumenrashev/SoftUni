package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.json.EmployeeCardJsonImportDto;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.EmployeeCardService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static hiberspring.common.Constants.*;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {

    private final EmployeeCardRepository employeeCardRepository;

    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository,
                                   ModelMapper modelMapper, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil) {
        this.employeeCardRepository = employeeCardRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEE_CARDS_PATH);
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(this.gson.fromJson(readEmployeeCardsJsonFile(), EmployeeCardJsonImportDto[].class))
                .forEach(ecdto->{
                    Optional<EmployeeCard> carByNumber =
                            this.employeeCardRepository.getByNumber(ecdto.getNumber());
                    if (carByNumber.isEmpty() && this.validationUtil.isValid(ecdto)){
                        this.employeeCardRepository.saveAndFlush(this.modelMapper.map(ecdto, EmployeeCard.class));
                        sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,EMPLOYEE_CARD,ecdto.getNumber()));
                    }else {
                        sb.append(INCORRECT_DATA_MESSAGE);
                    }
                    sb.append(System.lineSeparator());
                });
        return sb.toString();
    }
}
