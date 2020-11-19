package json.domain.dto.export;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UserExportDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private List<SoldProductExportDto> soldProducts;

    public UserExportDto() {
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

    public List<SoldProductExportDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<SoldProductExportDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
