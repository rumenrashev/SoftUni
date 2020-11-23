package softuni.exam.models.dtos.in;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public class PicturesJsonImportDto {

    @Expose
    @Length(min = 2 , max = 20)
    private String name;
    @Expose
    private LocalDateTime dateAndTime;
    @Expose
    private Long car;

    public PicturesJsonImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Long getCar() {
        return car;
    }

    public void setCar(Long car) {
        this.car = car;
    }
}
