package cardealder.domain.dto.out.task05;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.NONE)
public class CustomersTotalSalesDto {

    @XmlElement(name = "customer")
    private List<CustomerTotalSalesDto> customers;

    public CustomersTotalSalesDto() {
    }

    public List<CustomerTotalSalesDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerTotalSalesDto> customers) {
        this.customers = customers;
    }
}
