package productshop.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import productshop.service.api.CategoryService;
import productshop.service.api.ProductService;
import productshop.service.api.UserService;

@Component
public class AppRunner implements CommandLineRunner {

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    public AppRunner(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userService.seedUsers();
        this.categoryService.seedCategories();
        this.productService.seedProducts();
        this.productService.getProductInRangeWithoutBuyer();
        this.userService.getUsersSoldProducts();
        this.categoryService.getCategoriesByProductCount();
        this.userService.getUsersAndProducts();
        System.exit(0);
    }
}
