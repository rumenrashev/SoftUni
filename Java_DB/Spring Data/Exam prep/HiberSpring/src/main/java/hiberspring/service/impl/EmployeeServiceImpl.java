package hiberspring.service.impl;

import hiberspring.domain.dtos.xml.EmployeeXmlRootDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.service.EmployeeService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

import static hiberspring.common.Constants.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final EmployeeCardRepository employeeCardRepository;

    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository,
                               EmployeeCardRepository employeeCardRepository, ModelMapper modelMapper,
                               XmlParser xmlParser, FileUtil fileUtil, ValidationUtil validationUtil) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEES_PATH);
    }

    @Override
    public String importEmployees() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        this.xmlParser.parseXml(EmployeeXmlRootDto.class, EMPLOYEES_PATH)
                .getDtos()
                .forEach(e->{
                    Optional<EmployeeCard> card = this.employeeCardRepository.getByNumber(e.getCard());
                    Optional<Branch> branch = this.branchRepository.findByName(e.getBranch());
                    Optional<Employee> employeeByCard = this.employeeRepository.getByCard(e.getCard());
                    if (employeeByCard.isEmpty() && card.isPresent() && branch.isPresent() && this.validationUtil.isValid(e)){
                        Employee employee = this.modelMapper.map(e, Employee.class);
                        employee.setCard(card.get());
                        employee.setBranch(branch.get());
                        this.employeeRepository.saveAndFlush(employee);
                        sb.append(String.format(
                                SUCCESSFUL_IMPORT_MESSAGE,EMPLOYEE,e.getFirstName() + " "  + e.getLastName()));
                    }else {
                        sb.append(INCORRECT_DATA_MESSAGE);
                    }
                    sb.append(System.lineSeparator());
                });
        return sb.toString();
    }

    @Override
    public String exportProductiveEmployees() {
        return this.employeeRepository
                .getAllBy()
                .stream()
                .map(e->String.format(EXTRACT_MESSAGE,
                        e.getFirstName() + " " + e.getLastName(),
                        e.getPosition(),
                        e.getCard().getNumber()))
                .collect(Collectors.joining(LINE_SEPARATOR));
    }
}
