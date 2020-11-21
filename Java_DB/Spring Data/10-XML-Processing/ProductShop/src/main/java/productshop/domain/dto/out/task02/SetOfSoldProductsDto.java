package productshop.domain.dto.out.task02;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.NONE)
public class SetOfSoldProductsDto {

    @XmlElement(name = "product")
    private Set<SoldProductDto> soldProducts;

    public SetOfSoldProductsDto() {
    }

    public Set<SoldProductDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<SoldProductDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
