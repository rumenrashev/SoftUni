package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.in.PicturesJsonImportDto;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Picture;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    private static final String PICTURES_IN_PATH = "src/main/resources/files/json/pictures.json";


    private final PictureRepository pictureRepository;
    private final CarRepository carRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public PictureServiceImpl(PictureRepository pictureRepository, CarRepository carRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.pictureRepository = pictureRepository;
        this.carRepository = carRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(PICTURES_IN_PATH)));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();
        PicturesJsonImportDto[] pictureDtos = this.gson.fromJson(readPicturesFromFile(), PicturesJsonImportDto[].class);
        for (PicturesJsonImportDto pictureDto : pictureDtos) {
            Optional<Picture> byName =this.pictureRepository.getByName(pictureDto.getName());
            Optional<Car> byId = this.carRepository.findById(pictureDto.getCar());
            if (byName.isEmpty() && validationUtil.isValid(pictureDto)){
                Picture picture = this.modelMapper.map(pictureDto, Picture.class);
                Car car = byId.orElse(null);
                picture.setCar(car);
                this.pictureRepository.saveAndFlush(picture);
                sb.append(String.format("Successfully import picture - %s",picture.getName())).append(System.lineSeparator());
            }else {
                sb.append("Invalid picture").append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
