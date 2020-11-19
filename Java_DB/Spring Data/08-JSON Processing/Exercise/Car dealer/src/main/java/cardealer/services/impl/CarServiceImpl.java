package cardealer.services.impl;

import cardealer.common.RandomGenerator;
import cardealer.domain.dtos.in.CarInDto;
import cardealer.domain.dtos.out.CarByMakeDto;
import cardealer.domain.dtos.out.CarExportDto;
import cardealer.domain.dtos.out.PartExportDto;
import cardealer.domain.entities.Car;
import cardealer.domain.entities.Part;
import cardealer.domain.repositories.CarRepository;
import cardealer.domain.repositories.PartRepository;
import cardealer.services.interfaces.CarService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static cardealer.constants.Paths.*;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final RandomGenerator randomGenerator;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, Gson gson, ModelMapper modelMapper, RandomGenerator randomGenerator) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.randomGenerator = randomGenerator;
    }

    @Transactional
    @Override
    public void seedCars() throws IOException {
        String content = String.join(EMPTY_DELIMITER, Files.readAllLines(Path.of(CAR_IN_PATH)));
        CarInDto[] carInDtos = this.gson.fromJson(content, CarInDto[].class);
        for (CarInDto carInDto : carInDtos) {
            Car car = this.modelMapper.map(carInDto, Car.class);
            int partsCount = this.randomGenerator.generateRandomCountOfParts();
            for (int i = 1; i < partsCount ; i++) {
                Part part = getRandomPart();
                part.getCars().add(car);
                car.getParts().add(part);
            }
            this.carRepository.save(car);
        }
    }

    @Override
    public void getToyotaCats() throws IOException {
        List<Car> toyota = this.carRepository.getAllByMakeOrderByModelAlphabeticallyAndTravelledDistanceDesc("Toyota");
        CarByMakeDto[] carByMakeDtos = toyota
                .stream()
                .map(c -> modelMapper.map(c, CarByMakeDto.class))
                .toArray(CarByMakeDto[]::new);
        FileWriter writer = new FileWriter(TOYOTA_CARS);
        this.gson.toJson(carByMakeDtos,writer);
        writer.flush();
        writer.close();
    }

    @Override
    public void getCarsWithParts() throws IOException {
        List<Car> cars = this.carRepository.findAll();
        List<CarExportDto> carDtos = new ArrayList<>();
        for (Car car : cars) {
            CarExportDto carDto = modelMapper.map(car, CarExportDto.class);
            carDtos.add(carDto);
        }
        FileWriter writer = new FileWriter(CARS_PARTS);
        this.gson.toJson(carDtos,writer);
        writer.flush();
        writer.close();
    }


    private Part getRandomPart(){
        Long id = randomGenerator.generateId(this.partRepository.count());
        return this.partRepository.findById(id).orElse(null);
    }
}
