package json.service.impl;

import com.google.gson.Gson;
import json.constant.InOutPaths;
import json.domain.dto.CategorySeedDto;
import json.domain.dto.export.CategoryExportDto;
import json.domain.entities.Category;
import json.domain.entities.Product;
import json.domain.repositories.CategoryRepository;
import json.domain.repositories.ProductRepository;
import json.domain.repositories.UserRepository;
import json.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final Gson gson;
    private final Random random;

    @Autowired
    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryRepository,
                               UserRepository userRepository, ProductRepository productRepository,
                               Gson gson, Random random) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.gson = gson;
        this.random = random;
    }

    @Override
    @Transactional
    public void seedCategories() throws IOException {
        String fileContent = String.join("", Files.readAllLines(Path.of(InOutPaths.CATEGORIES_IN)));
        CategorySeedDto[] CategorySeedDtos = this.gson.fromJson(fileContent, CategorySeedDto[].class);
        for (CategorySeedDto CategorySeedDto : CategorySeedDtos) {
            Category category = this.modelMapper.map(CategorySeedDto, Category.class);
            this.categoryRepository.saveAndFlush(category);
            for (int i = 0; i < this.random.nextInt((int)this.categoryRepository.count()) + 1 ; i++) {
                setRandomCategory(category);
            }
        }
    }

    @Override
    public void getAllCategories() {
        List<Category> all = this.categoryRepository.findAllOrderByProductsCount();
        List<CategoryExportDto> exportDtos = new ArrayList<>();
        for (Category category : all) {
            String categoryName = category.getName();
            int productCount = category.getProducts().size();
            BigDecimal totalRevenue = getTotalRevenue(category);
            BigDecimal averagePrice = totalRevenue.divide(BigDecimal.valueOf(productCount),6, RoundingMode.CEILING);
            CategoryExportDto exportDto = new CategoryExportDto();
            exportDto.setCategory(categoryName);
            exportDto.setAveragePrice(averagePrice);
            exportDto.setTotalRevenue(totalRevenue);
            exportDto.setProductsCount(productCount);
            exportDtos.add(exportDto);
        }
        try {
            FileWriter writer = new FileWriter(InOutPaths.TASK_03_OUT);
            this.gson.toJson(exportDtos,writer);
            writer.flush();
            writer.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }


    private BigDecimal getTotalRevenue(Category category) {
        BigDecimal totalRevenue = BigDecimal.ZERO;
        for (Product product : category.getProducts()) {
            totalRevenue = totalRevenue.add(product.getPrice());
        }
        return totalRevenue;
    }

    private void setRandomCategory(Category category) {
        long id = (long) this.random.nextInt((int) this.categoryRepository.count()) + 1;
        this.productRepository.findById(id).get().getCategories().add(category);
    }

}
