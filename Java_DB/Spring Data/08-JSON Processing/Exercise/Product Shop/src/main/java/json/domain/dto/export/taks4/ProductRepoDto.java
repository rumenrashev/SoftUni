package json.domain.dto.export.taks4;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ProductRepoDto {

    @Expose
    private long count;

    @Expose
    List<ProductViewDto> products = new ArrayList<>();

    public ProductRepoDto() {
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<ProductViewDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductViewDto> products) {
        this.products = products;
    }
}
