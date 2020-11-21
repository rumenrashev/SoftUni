package productshop.domain.dto.in.category;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.NONE)
public class CategoryCollectionXmlImportDto {

    @XmlElement(name = "category")
    private List<CategoryXmlImportDto> categories;

    public CategoryCollectionXmlImportDto() {
    }

    public List<CategoryXmlImportDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryXmlImportDto> categories) {
        this.categories = categories;
    }
}
