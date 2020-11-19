package json.service;

import java.io.IOException;

public interface CategoryService {

    public void seedCategories() throws IOException;

    public void getAllCategories();

}
