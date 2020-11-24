package softuni.exam.models.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "to-town")
@XmlAccessorType(XmlAccessType.NONE)
public class ToTownXmlDto {

    @XmlElement()
    private String name;

    public ToTownXmlDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
