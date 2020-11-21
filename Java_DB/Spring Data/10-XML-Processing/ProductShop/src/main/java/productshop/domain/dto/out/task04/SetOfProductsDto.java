package productshop.domain.dto.out.task04;

import productshop.domain.dto.out.task02.SoldProductDto;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.NONE)
public class SetOfProductsDto {

    @XmlElement(name = "product")
    private Set<ProductDto> soldProducts;

    @XmlAttribute(name = "count")
    private int count;

    public SetOfProductsDto() {
    }

    public Set<ProductDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductDto> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
