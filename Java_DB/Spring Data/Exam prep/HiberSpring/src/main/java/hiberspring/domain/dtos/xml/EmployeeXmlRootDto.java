package hiberspring.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.NONE)
public class EmployeeXmlRootDto {

    @XmlElement(name = "employee")
    private List<EmployeeXmlDto> dtos;

    public EmployeeXmlRootDto() {
    }

    public List<EmployeeXmlDto> getDtos() {
        return dtos;
    }

    public void setDtos(List<EmployeeXmlDto> dtos) {
        this.dtos = dtos;
    }
}
