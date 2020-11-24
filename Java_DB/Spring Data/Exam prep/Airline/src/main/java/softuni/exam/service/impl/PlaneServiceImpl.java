package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.xml.PlaneXmlImportDto;
import softuni.exam.models.dtos.xml.PlanesRootDto;
import softuni.exam.models.entities.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static softuni.exam.constants.GlobalConstants.*;

@Service
public class PlaneServiceImpl implements PlaneService {

    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public PlaneServiceImpl(PlaneRepository planeRepository, ModelMapper modelMapper, XmlParser xmlParser, 
                            ValidationUtil validationUtil) {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files.readString(Path.of(PLANES_IMPORT_PATH));
    }

    @Override
    public String importPlanes() throws JAXBException {
        StringBuilder sb =new StringBuilder();
        PlanesRootDto planesRootDto = this.xmlParser.fromXmlDocument(PlanesRootDto.class, PLANES_IMPORT_PATH);
        List<PlaneXmlImportDto> planeDtos = planesRootDto.getPlaneDtos();
        for (PlaneXmlImportDto planeDto : planeDtos) {
            Optional<Plane> byRegisterNumber =
                    this.planeRepository.findByRegisterNumber(planeDto.getRegisterNumber());
            if (byRegisterNumber.isEmpty() && this.validationUtil.isValid(planeDto)){
                Plane plane = this.modelMapper.map(planeDto, Plane.class);
                this.planeRepository.saveAndFlush(plane);
                sb.append(String.format(IMPORT_PLANE,plane.getRegisterNumber()));
            }else {
                sb.append(INVALID_PLANE);
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
