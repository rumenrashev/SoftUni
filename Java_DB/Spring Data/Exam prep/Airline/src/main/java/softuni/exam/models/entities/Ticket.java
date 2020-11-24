package softuni.exam.models.entities;

import softuni.exam.models.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {

    private String serialNumber;
    private BigDecimal price;
    private LocalDateTime takeoff;
    private Town fromTown;
    private Town toTown;
    private Passenger passenger;
    private Plane plane;

    public Ticket() {
    }

    @Column(nullable = false, unique = true)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Column(nullable = false)
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_town_id")
    public Town getFromTown() {
        return fromTown;
    }

    public void setFromTown(Town fromTown) {
        this.fromTown = fromTown;
    }

    @ManyToOne()
    @JoinColumn(name = "to_town_id")
    public Town getToTown() {
        return toTown;
    }

    public void setToTown(Town toTown) {
        this.toTown = toTown;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
}
