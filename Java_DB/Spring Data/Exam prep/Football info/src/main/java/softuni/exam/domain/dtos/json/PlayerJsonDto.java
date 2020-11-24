package softuni.exam.domain.dtos.json;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import softuni.exam.domain.enums.Position;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PlayerJsonDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Integer number;

    @Expose
    private Position position;

    @Expose
    private BigDecimal salary;

    @Expose
    private PictureJsonDto picture;

    @Expose
    private TeamJsonDto team;

    public PlayerJsonDto() {
    }

    @NotNull
    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Length(min = 3, max = 15)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Min(1)
    @Max(99)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @NotNull
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @NotNull
    @DecimalMin("0")
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public PictureJsonDto getPicture() {
        return picture;
    }

    public void setPicture(PictureJsonDto picture) {
        this.picture = picture;
    }

    public TeamJsonDto getTeam() {
        return team;
    }

    public void setTeam(TeamJsonDto team) {
        this.team = team;
    }
}
