package productshop.domain.dto.out.task01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.NONE)
public class ProductsInRangeDto {

    @XmlElement(name = "product")
    private List<ProductNamePriceSellerDto> products = new ArrayList<>();

    public ProductsInRangeDto() {
    }

    public List<ProductNamePriceSellerDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductNamePriceSellerDto> products) {
        this.products = products;
    }
}
