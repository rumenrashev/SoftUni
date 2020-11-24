package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.json.PassengerJsonImportDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

import static softuni.exam.constants.GlobalConstants.*;



@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public PassengerServiceImpl(PassengerRepository passengerRepository, TownRepository townRepository,
                                ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.passengerRepository = passengerRepository;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files.readString(Path.of(PASSENGER_IMPORT_PATH));
    }

    @Override
    public String importPassengers() throws IOException {
        StringBuilder sb = new StringBuilder();
        PassengerJsonImportDto[] passengerJsonImportDtos =
                this.gson.fromJson(readPassengersFileContent(), PassengerJsonImportDto[].class);
        for (PassengerJsonImportDto passengerJsonImportDto : passengerJsonImportDtos) {

            Optional<Passenger> passengerByEmail =
                    this.passengerRepository.findByEmail(passengerJsonImportDto.getEmail());

            if (passengerByEmail.isEmpty() && this.validationUtil.isValid(passengerJsonImportDto)){
                Passenger passenger = this.modelMapper.map(passengerJsonImportDto, Passenger.class);
                String townName = passengerJsonImportDto.getTown();
                Town town = this.townRepository.findByName(townName).orElse(null);
                passenger.setTown(town);
                this.passengerRepository.saveAndFlush(passenger);
                sb.append(String.format(IMPORT_PASSENGER,passenger.getLastName(),passenger.getEmail()));
            }else {
                sb.append(INVALID_PASSENGER);
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        return this.passengerRepository
                .getByOrderByTicketsCountDescAndEmail()
                .stream()
                .map(p-> String.format(DATA_EXPORT_MESSAGE,
                        p.getFirstName(),
                        p.getLastName(),
                        p.getEmail(),
                        p.getPhoneNumber(),
                        p.getTickets().size()))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
