package productshop.domain.dto.in.product;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.NONE)
public class ProductCollectionXmlImportDto {

    @XmlElement(name = "product")
    private List<ProductXmlImportDto> products = new ArrayList<>();


    public ProductCollectionXmlImportDto() {
    }

    public List<ProductXmlImportDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductXmlImportDto> products) {
        this.products = products;
    }
}
