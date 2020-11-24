package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.xml.PictureRootDto;
import softuni.exam.domain.dtos.xml.PictureXmlDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static softuni.exam.constants.GlobalConstants.*;

@Service
public class PictureServiceImpl implements PictureService {

    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final PictureRepository pictureRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;

    @Autowired
    public PictureServiceImpl(ModelMapper modelMapper, ValidatorUtil validatorUtil, PictureRepository pictureRepository, FileUtil fileUtil, XmlParser xmlParser) {
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.pictureRepository = pictureRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public String importPictures() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        PictureRootDto pictureRootDto = this.xmlParser.fromXML(PictureRootDto.class, PICTURE_PATH);
        for (PictureXmlDto pictureXmlDto : pictureRootDto.getPictureXmlDtos()) {
            if (this.validatorUtil.isValid(pictureXmlDto)){
                Picture picture = this.modelMapper.map(pictureXmlDto, Picture.class);
                this.pictureRepository.saveAndFlush(picture);
                sb.append(String.format(PICTURE_IMPORTED,picture.getUrl()));
            }else {
                sb.append(INVALID_PICTURE);
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
            return this.fileUtil.readFile(PICTURE_PATH);
    }

}
