package softuni.exam.domain.dtos.xml;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.NONE)
public class TeamXmlDto {

    @XmlElement
    private String name;

    @XmlElement(name = "picture")
    private PictureXmlDto pictureXmlDto;

    public TeamXmlDto() {
    }

    @NotNull
    @Length(min = 3,max = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PictureXmlDto getPictureXmlDto() {
        return pictureXmlDto;
    }

    public void setPictureXmlDto(PictureXmlDto pictureXmlDto) {
        this.pictureXmlDto = pictureXmlDto;
    }
}
