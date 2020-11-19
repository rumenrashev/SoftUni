package json.service;

import java.io.IOException;

public interface ProductService {

    public void seedProducts() throws IOException;

    public void exportProductsWithoutBuyerAndPriceBetween();

}
