package json.init;

import json.service.CategoryService;
import json.service.ProductService;
import json.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements CommandLineRunner {

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    public AppInitializer(UserService userService, CategoryService categoryService, ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userService.seedUsers();
        this.productService.seedProducts();
        this.categoryService.seedCategories();

        this.productService.exportProductsWithoutBuyerAndPriceBetween();
        this.userService.getAllWithAtLeastOneSoldItem();
        this.categoryService.getAllCategories();
        this.userService.selectAllUsersWithSoldItem();


        System.exit(0);
    }
}
