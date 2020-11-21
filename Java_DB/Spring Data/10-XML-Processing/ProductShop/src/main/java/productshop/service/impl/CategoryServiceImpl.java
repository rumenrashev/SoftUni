package productshop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import productshop.domain.dto.in.category.CategoryCollectionXmlImportDto;
import productshop.domain.dto.out.task3.CategoryDto;
import productshop.domain.dto.out.task3.SetOfCategoriesDto;
import productshop.domain.entity.Category;
import productshop.domain.entity.Product;
import productshop.domain.entity.User;
import productshop.domain.repository.CategoryRepository;
import productshop.service.api.CategoryService;
import productshop.util.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import static productshop.constants.FilePaths.CATEGORIES_BY_PRODUCTS;
import static productshop.constants.FilePaths.CATEGORIES_IN;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final Random random;


    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.random = new Random();
    }

    @Transactional
    @Override
    public void seedCategories() throws JAXBException {
        this.xmlParser.fromXml(CategoryCollectionXmlImportDto.class, CATEGORIES_IN)
                .getCategories()
                .stream()
                .map(mdto -> this.modelMapper.map(mdto, Category.class))
                .forEach(this.categoryRepository::save);
    }

    @Override
    public void getCategoriesByProductCount() throws JAXBException {
        Set<Category> categories = this.categoryRepository.getAllOrderByProductsCount();
        Set<CategoryDto> dtos = new LinkedHashSet<>();
        for (Category category : categories) {
            CategoryDto dto = new CategoryDto();
            int productsCount = category.getProducts().size();
            BigDecimal totalRevenue = BigDecimal.ZERO;
            for (Product product : category.getProducts()) {
                totalRevenue = totalRevenue.add(product.getPrice());
            }
            BigDecimal averagePrice = BigDecimal.ZERO;
            if (productsCount > 0){
                averagePrice = totalRevenue.divide(new BigDecimal(productsCount),6,RoundingMode.CEILING);
            }
            dto.setName(category.getName());
            dto.setProductCount(productsCount);
            dto.setTotalRevenue(totalRevenue);
            dto.setAveragePrice(averagePrice);

            dtos.add(dto);
        }
        SetOfCategoriesDto setOfCategoriesDto = new SetOfCategoriesDto();
        setOfCategoriesDto.setCategories(dtos);
        this.xmlParser.toXml(SetOfCategoriesDto.class,setOfCategoriesDto,CATEGORIES_BY_PRODUCTS);
    }
}
