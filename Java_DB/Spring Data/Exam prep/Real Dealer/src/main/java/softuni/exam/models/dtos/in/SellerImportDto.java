package softuni.exam.models.dtos.in;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "seller")
@XmlAccessorType(XmlAccessType.NONE)
public class SellerImportDto {

    @XmlElement(name = "first-name")
    @Length(min = 2, max = 20)
    private String firstName;

    @XmlElement(name = "last-name")
    @Length(min = 2, max = 20)
    private String lastName;

    @XmlElement(name = "email")
    @Pattern(regexp = ".*@.*\\..*|.*\\..*@.*")
    private String email;

    @XmlElement(name = "rating")
    private String rating;

    @XmlElement(name = "town")
    @NotNull
    private String town;

    public SellerImportDto() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
