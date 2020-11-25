package hiberspring.domain.dtos.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownJsonImportDto {

    @Expose
    private String name;

    @Expose
    private Integer population;

    public TownJsonImportDto() {
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
