package softuni.exam.models.dtos.in;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import java.time.LocalDate;

public class CarJsonImportDto {

    @Expose
    @Length(min = 2, max = 20)
    private String make;

    @Expose
    @Length(min = 2, max = 20)
    private String model;

    @Expose
    @Min(0)
    private int kilometers;

    @Expose
    private LocalDate registeredOn;

    public CarJsonImportDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }
}
