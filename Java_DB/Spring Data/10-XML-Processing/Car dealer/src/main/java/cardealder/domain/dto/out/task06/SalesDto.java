package cardealder.domain.dto.out.task06;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.NONE)
public class SalesDto {

    @XmlElement(name = "sale")
    private List<SaleDto> sales;

    public SalesDto() {
    }

    public List<SaleDto> getSales() {
        return sales;
    }

    public void setSales(List<SaleDto> sales) {
        this.sales = sales;
    }
}
