package softuni.exam.models.dtos.json;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TownJsonImportDto {

    @Expose
    private String name;
    @Expose
    private Integer population;
    @Expose
    private String guide;

    public TownJsonImportDto() {
    }

    @Length(min = 2)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(0)
    @NotNull
    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
}
