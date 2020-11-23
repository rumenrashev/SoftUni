package softuni.exam.models.dtos.in;

import org.hibernate.validator.constraints.Length;
import softuni.exam.util.XmlLocalDateTimeAdapter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.NONE)
public class OfferImportDto {

    @XmlElement(name = "description")
    @Length(min = 5)
    private String description;

    @XmlElement(name = "price")
    @DecimalMin("0")
    private BigDecimal price;

    @XmlElement(name = "added-on")
    @XmlJavaTypeAdapter(XmlLocalDateTimeAdapter.class)
    private LocalDateTime addedOn;

    @XmlElement(name = "has-gold-status")
    @NotNull
    private Boolean hasGoldStatus;

    @XmlElement(name = "car")
    private CarXmlImportDto car;

    @XmlElement(name = "seller")
    private SellerXmlImportDto seller;

    public OfferImportDto() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public Boolean getHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(Boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public CarXmlImportDto getCar() {
        return car;
    }

    public void setCar(CarXmlImportDto car) {
        this.car = car;
    }

    public SellerXmlImportDto getSeller() {
        return seller;
    }

    public void setSeller(SellerXmlImportDto seller) {
        this.seller = seller;
    }
}
