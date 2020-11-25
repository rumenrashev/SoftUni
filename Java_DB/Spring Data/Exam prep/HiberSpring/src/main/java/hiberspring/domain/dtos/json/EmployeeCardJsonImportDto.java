package hiberspring.domain.dtos.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class EmployeeCardJsonImportDto {

    @Expose
    private String number;

    public EmployeeCardJsonImportDto() {
    }

    @NotNull
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
