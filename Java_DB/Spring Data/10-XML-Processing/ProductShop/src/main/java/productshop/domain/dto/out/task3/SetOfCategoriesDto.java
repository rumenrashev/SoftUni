package productshop.domain.dto.out.task3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.NONE)
public class SetOfCategoriesDto {

    @XmlElement(name = "category")
    private Set<CategoryDto> categories;

    public SetOfCategoriesDto() {
    }

    public Set<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDto> categories) {
        this.categories = categories;
    }
}
