package softuni.exam.models.dtos.xml;

import org.hibernate.validator.constraints.Length;
import softuni.exam.adapters.XmlLocalDateTimeAdapter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketXmlImportDto {

    @XmlElement(name = "serial-number")
    private String serialNumber;

    @XmlElement
    private BigDecimal price;

    @XmlElement(name = "take-off")
    @XmlJavaTypeAdapter(XmlLocalDateTimeAdapter.class)
    private LocalDateTime takeoff;

    @XmlElement(name = "from-town")
    private FromTownXmlDto fromTown;

    @XmlElement(name = "to-town")
    private ToTownXmlDto toTown;

    @XmlElement(name = "passenger")
    private PassengerXmlDto passenger;

    @XmlElement(name = "plane")
    private PlaneXmlDto plane;

    public TicketXmlImportDto() {
    }

    @NotNull
    @Length(min = 2)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @NotNull
    @DecimalMin("2")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getTakeoff() {
        return takeoff;
    }

    public void setTakeoff(LocalDateTime takeoff) {
        this.takeoff = takeoff;
    }

    public FromTownXmlDto getFromTown() {
        return fromTown;
    }

    public void setFromTown(FromTownXmlDto fromTown) {
        this.fromTown = fromTown;
    }

    public ToTownXmlDto getToTown() {
        return toTown;
    }

    public void setToTown(ToTownXmlDto toTown) {
        this.toTown = toTown;
    }

    public PassengerXmlDto getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerXmlDto passenger) {
        this.passenger = passenger;
    }

    public PlaneXmlDto getPlane() {
        return plane;
    }

    public void setPlane(PlaneXmlDto plane) {
        this.plane = plane;
    }
}
