package cardealer.domain.dtos.in;

import com.google.gson.annotations.Expose;

public class SupplierInDto {
    @Expose
    private String name;

    @Expose
    private Boolean isImporter;

    public SupplierInDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}
