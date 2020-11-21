package productshop.domain.dto.out.task02;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.NONE)
public class UserWithSoldProductsDto {

    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlElement(name = "sold-products")
    private SetOfSoldProductsDto soldProducts;

    public UserWithSoldProductsDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SetOfSoldProductsDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SetOfSoldProductsDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
