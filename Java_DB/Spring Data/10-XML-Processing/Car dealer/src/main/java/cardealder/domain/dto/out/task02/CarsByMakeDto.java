package cardealder.domain.dto.out.task02;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.NONE)
public class CarsByMakeDto {

    @XmlElement(name = "car")
    private Set<CarByMakeDto> cars;

    public CarsByMakeDto() {
    }

    public Set<CarByMakeDto> getCars() {
        return cars;
    }

    public void setCars(Set<CarByMakeDto> cars) {
        this.cars = cars;
    }
}
