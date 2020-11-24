package softuni.exam.models.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.NONE)
public class PlanesRootDto {

    @XmlElement(name = "plane")
    private List<PlaneXmlImportDto> planeDtos;

    public PlanesRootDto() {
    }

    public List<PlaneXmlImportDto> getPlaneDtos() {
        return planeDtos;
    }

    public void setPlaneDtos(List<PlaneXmlImportDto> planeDtos) {
        this.planeDtos = planeDtos;
    }
}
