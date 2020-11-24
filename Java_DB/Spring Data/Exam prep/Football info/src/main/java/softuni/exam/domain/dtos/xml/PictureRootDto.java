package softuni.exam.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "pictures")
@XmlAccessorType(XmlAccessType.NONE)
public class PictureRootDto {

    @XmlElement(name = "picture")
    private List<PictureXmlDto> pictureXmlDtos;

    public PictureRootDto() {
    }

    public List<PictureXmlDto> getPictureXmlDtos() {
        return pictureXmlDtos;
    }

    public void setPictureXmlDtos(List<PictureXmlDto> pictureXmlDtos) {
        this.pictureXmlDtos = pictureXmlDtos;
    }
}
