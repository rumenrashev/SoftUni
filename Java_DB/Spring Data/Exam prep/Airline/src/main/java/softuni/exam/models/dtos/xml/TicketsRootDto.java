package softuni.exam.models.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.NONE)
public class TicketsRootDto {

    @XmlElement(name = "ticket")
    private List<TicketXmlImportDto> ticketDtos;

    public TicketsRootDto() {
    }

    public List<TicketXmlImportDto> getTicketDtos() {
        return ticketDtos;
    }

    public void setTicketDtos(List<TicketXmlImportDto> ticketDtos) {
        this.ticketDtos = ticketDtos;
    }
}
