package json.domain.dto.export.taks4;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UserViewDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private int age;

    @Expose
    private ProductRepoDto soldProducts;

    public UserViewDto() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ProductRepoDto getProductRepoDto() {
        return soldProducts;
    }

    public void setProductRepoDto(ProductRepoDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
