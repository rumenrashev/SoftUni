package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.xml.TicketXmlImportDto;
import softuni.exam.models.dtos.xml.TicketsRootDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Plane;
import softuni.exam.models.entities.Ticket;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TicketService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static softuni.exam.constants.GlobalConstants.*;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final TownRepository townRepository;
    private final PassengerRepository passengerRepository;
    private final PlaneRepository planeRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper, XmlParser xmlParser,
                             ValidationUtil validationUtil, TownRepository townRepository,
                             PassengerRepository passengerRepository, PlaneRepository planeRepository) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.townRepository = townRepository;
        this.passengerRepository = passengerRepository;
        this.planeRepository = planeRepository;
    }

    @Override
    public boolean areImported() {
        return this.ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files.readString(Path.of(TICKETS_IMPORT_PATH));
    }

    @Transactional
    @Override
    public String importTickets() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        TicketsRootDto ticketsRootDto =
                this.xmlParser.fromXmlDocument(TicketsRootDto.class, TICKETS_IMPORT_PATH);
        List<TicketXmlImportDto> ticketDtos = ticketsRootDto.getTicketDtos();
        for (TicketXmlImportDto ticketDto : ticketDtos) {
            Optional<Ticket> bySerialNumber =
                    this.ticketRepository.findBySerialNumber(ticketDto.getSerialNumber());
            if (bySerialNumber.isEmpty() && this.validationUtil.isValid(ticketDto)){
                Ticket ticket = this.modelMapper.map(ticketDto, Ticket.class);
                Town fromTown = this.townRepository.findByName(ticketDto.getFromTown()
                                .getName())
                                .orElse(null);
                Town toTown = this.townRepository.findByName(ticketDto.getToTown()
                        .getName())
                        .orElse(null);

                Passenger passenger = this.passengerRepository.findByEmail(ticket.getPassenger()
                        .getEmail())
                        .orElse(null);

                Plane plane = this.planeRepository.findByRegisterNumber(ticket.getPlane()
                        .getRegisterNumber())
                        .orElse(null);


                ticket.setFromTown(fromTown);
                ticket.setToTown(toTown);
                ticket.setPassenger(passenger);
                ticket.setPlane(plane);

                this.ticketRepository.saveAndFlush(ticket);

                sb.append(String.format(IMPORT_TICKET
                        ,ticket.getFromTown().getName()
                        ,ticket.getToTown().getName()));
            }else {
                sb.append(INVALID_TICKET);
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
