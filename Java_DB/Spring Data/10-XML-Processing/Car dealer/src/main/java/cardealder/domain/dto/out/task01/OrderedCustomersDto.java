package cardealder.domain.dto.out.task01;

import cardealder.domain.entity.Customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.NONE)
public class OrderedCustomersDto {

    @XmlElement(name = "customer")
    private Set<OrderedCustomerDto> customers;

    public OrderedCustomersDto() {
    }

    public Set<OrderedCustomerDto> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<OrderedCustomerDto> customers) {
        this.customers = customers;
    }
}
