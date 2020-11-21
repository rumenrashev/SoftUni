package productshop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import productshop.domain.dto.in.product.ProductCollectionXmlImportDto;
import productshop.domain.dto.out.task01.ProductNamePriceSellerDto;
import productshop.domain.dto.out.task01.ProductsInRangeDto;
import productshop.domain.entity.Category;
import productshop.domain.entity.Product;
import productshop.domain.repository.CategoryRepository;
import productshop.domain.repository.ProductRepository;
import productshop.domain.repository.UserRepository;
import productshop.service.api.ProductService;
import productshop.util.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;

import java.math.BigDecimal;
import java.util.*;

import static productshop.constants.FilePaths.PRODUCTS_IN;
import static productshop.constants.FilePaths.PRODUCTS_INT_RANGE;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final Random random;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                              UserRepository userRepository,
                              ModelMapper modelMapper, XmlParser xmlParser) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.random = new Random();
    }

    @Transactional
    @Override
    public void seedProducts() throws JAXBException {

        this.xmlParser.fromXml(ProductCollectionXmlImportDto.class, PRODUCTS_IN)
                .getProducts()
                .stream()
                .map(pdto -> this.modelMapper.map(pdto, Product.class))
                .forEach(p -> {
                    p.setBuyer(this.userRepository.getRandomUser());
                    p.setSeller(this.userRepository.getRandomUser());
                    p.setCategories(generatesRandomCategories());
                    this.productRepository.save(p);
                });
        // leave some product without buyer
        this.productRepository.getRandomProduct().setBuyer(null);
        this.productRepository.getRandomProduct().setBuyer(null);
        this.productRepository.getRandomProduct().setBuyer(null);
    }

    @Override
    public void getProductInRangeWithoutBuyer() throws JAXBException {
        final BigDecimal min = new BigDecimal(500);
        final BigDecimal max = new BigDecimal(1000);
        List<Product> products = this.productRepository.getAllByBuyerIsNullAndPriceBetween(min, max);
        ProductsInRangeDto productsInRangeDto = new ProductsInRangeDto();
        for (Product product : products) {
            ProductNamePriceSellerDto dto = this.modelMapper.map(product, ProductNamePriceSellerDto.class);
            if (product.getSeller().getFirstName() != null){
                dto.setSeller(product.getSeller().getFirstName() + " " + product.getSeller().getLastName());
            }else {
                dto.setSeller(product.getSeller().getLastName());
            }
            productsInRangeDto.getProducts().add(dto);
        }
        this.xmlParser.toXml(ProductsInRangeDto.class,productsInRangeDto,PRODUCTS_INT_RANGE);
    }

    private Set<Category> generatesRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int randomCount = random.nextInt(10) + 1;
        for (int i = 1; i < randomCount; i++) {
            categories.add(this.categoryRepository.getRandomCategory());
        }
        return categories;
    }
}
