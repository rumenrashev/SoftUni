package hiberspring.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.NONE)
public class ProductsXmlRootDto {

    @XmlElement(name = "product")
    private List<ProductXmlDto> dtos;

    public ProductsXmlRootDto() {
    }

    public List<ProductXmlDto> getDtos() {
        return dtos;
    }

    public void setDtos(List<ProductXmlDto> dtos) {
        this.dtos = dtos;
    }
}
