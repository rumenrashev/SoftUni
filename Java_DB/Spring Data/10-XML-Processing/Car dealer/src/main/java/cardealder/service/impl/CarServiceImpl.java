package cardealder.service.impl;

import cardealder.domain.dto.in.CarsImportDto;
import cardealder.domain.dto.out.task02.CarByMakeDto;
import cardealder.domain.dto.out.task02.CarsByMakeDto;
import cardealder.domain.dto.out.task04.CarWithPartsDto;
import cardealder.domain.dto.out.task04.CarsWithPartsDto;
import cardealder.domain.entity.Car;
import cardealder.domain.entity.Part;
import cardealder.domain.repository.*;
import cardealder.service.api.CarService;
import cardealder.util.api.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.util.*;

import static cardealder.constants.FilePaths.*;

@Service
public class CarServiceImpl extends BaseService implements CarService {

    private final Random random;

    public CarServiceImpl(ModelMapper modelMapper, XmlParser xmlParser, CarRepository carRepository, PartRepository partRepository, CustomerRepository customerRepository, SaleRepository saleRepository, SupplierRepository supplierRepository) {
        super(modelMapper, xmlParser, carRepository, partRepository, customerRepository, saleRepository, supplierRepository);
        this.random = new Random();
    }

    @Override
    @Transactional
    public void seed() throws JAXBException {
        xmlParser.fromXML(CarsImportDto.class, CARS)
                .getCars()
                .stream()
                .map(cdto -> modelMapper.map(cdto, Car.class))
                .forEach(c -> {
                    List<Part> parts = generateRandomParts();
                    for (Part part : parts) {
                        part.getCars().add(c);
                    }
                    c.setParts(parts);
                    carRepository.save(c);
                });
    }

    private List<Part> generateRandomParts() {
        int count = random.nextInt(3) + 1;
        return this.partRepository.getRandomParts(count);
    }

    @Override
    public void getToyotaCars() throws JAXBException {
        CarsByMakeDto carsByMakeDto = new CarsByMakeDto();
        Set<CarByMakeDto> carsDto = new LinkedHashSet<>();
        this.carRepository.getAllByModelOrderByModelAndTravelledDistanceDesc("Toyota")
                .stream()
                .map(c-> this.modelMapper.map(c,CarByMakeDto.class))
                .forEach(carsDto::add);
        carsByMakeDto.setCars(carsDto);
        xmlParser.toXML(CarsByMakeDto.class,carsByMakeDto,TOYOTA_CARS);
    }

    @Override
    public void getAllCarsAndParts() throws JAXBException {
        CarsWithPartsDto carsWithPartsDto = new CarsWithPartsDto();
        List<Car> cars = this.carRepository.findAll();
        List<CarWithPartsDto> carsDto = new ArrayList<>();
        for (Car car : cars) {
            CarWithPartsDto carDto = this.modelMapper.map(car, CarWithPartsDto.class);
            carsDto.add(carDto);
        }
        carsWithPartsDto.setCars(carsDto);
        xmlParser.toXML(CarsWithPartsDto.class,carsWithPartsDto,CARS_AND_PARTS);
    }
}
