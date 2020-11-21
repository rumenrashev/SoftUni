package productshop.service.api;

import javax.xml.bind.JAXBException;

public interface CategoryService {

    void seedCategories() throws JAXBException;

    void getCategoriesByProductCount() throws JAXBException;

}
