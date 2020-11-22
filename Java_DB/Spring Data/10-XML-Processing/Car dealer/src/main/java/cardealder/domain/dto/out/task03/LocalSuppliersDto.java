package cardealder.domain.dto.out.task03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.NONE)
public class LocalSuppliersDto {

    @XmlElement(name = "supplier")
    private Set<LocalSupplierDto> suppliers;

    public LocalSuppliersDto() {
    }

    public Set<LocalSupplierDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<LocalSupplierDto> suppliers) {
        this.suppliers = suppliers;
    }
}
