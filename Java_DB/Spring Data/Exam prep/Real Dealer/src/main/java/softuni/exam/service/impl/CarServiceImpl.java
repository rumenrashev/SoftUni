package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Strings;
import softuni.exam.models.dtos.in.CarJsonImportDto;
import softuni.exam.models.entities.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {

    private static final String CARS_IMPORT_PATH = "src/main/resources/files/json/cars.json";

    private final CarRepository carRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CarServiceImpl(CarRepository carRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(Path.of(CARS_IMPORT_PATH));
    }

    @Override
    public String importCars() throws IOException {
        StringBuilder sb = new StringBuilder();
        CarJsonImportDto[] carDtos = this.gson.fromJson(readCarsFileContent(), CarJsonImportDto[].class);
        for (CarJsonImportDto carDto : carDtos) {
            Optional<Car> optionalCar = this.carRepository.getCarByMakeAndModelAndKilometers(
                    carDto.getMake(),
                    carDto.getModel(),
                    carDto.getKilometers());

            if (this.validationUtil.isValid(carDto) && optionalCar.isEmpty()){
                Car car = this.modelMapper.map(carDto, Car.class);
                this.carRepository.saveAndFlush(car);
                sb.append(String.format("Successfully imported car - %s - %s",car.getMake(),car.getModel()))
                        .append(System.lineSeparator());
            }else {
                sb.append("Invalid car")
                        .append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        StringBuilder sb = new StringBuilder();
        Set<Car> cars = this.carRepository.getAllByPicturesCountDescAndByModelAlphabetically();
        for (Car car : cars) {
            sb.append(String.format("Car make - %s, model - %s",car.getMake(),car.getModel()))
                    .append(System.lineSeparator())
                    .append(String.format("\tKilometers - %s",car.getKilometers()))
                    .append(System.lineSeparator())
                    .append(String.format("\tRegistered on - %s",car.getRegisteredOn().toString()))
                    .append(System.lineSeparator())
                    .append(String.format("\tNumber of pictures - %s",car.getPictures().size()))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}
