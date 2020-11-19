package json.service.impl;

import com.google.gson.Gson;
import json.constant.InOutPaths;
import json.domain.dto.ProductSeedDto;
import json.domain.dto.export.ProductExportDto;
import json.domain.entities.Product;
import json.domain.repositories.ProductRepository;
import json.domain.repositories.UserRepository;
import json.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final Gson gson;
    private final Random random;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository,
                              UserRepository userRepository, Gson gson, Random random) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.gson = gson;
        this.random = random;
    }

    @Override
    @Transactional
    public void seedProducts() throws IOException {
        String fileContent = String.join("", Files.readAllLines(Path.of(InOutPaths.PRODUCTS_IN)));
        ProductSeedDto[] ProductSeedDtos = this.gson.fromJson(fileContent, ProductSeedDto[].class);
        for (ProductSeedDto ProductSeedDto : ProductSeedDtos) {
            Product product = this.modelMapper.map(ProductSeedDto, Product.class);
            this.productRepository.saveAndFlush(product);
            setRandomBuyer(product);
            setRandomSeller(product);
        }
    }

    @Override
    public void exportProductsWithoutBuyerAndPriceBetween() {
        final BigDecimal min = new BigDecimal("500");
        final BigDecimal max = new BigDecimal("1000");

        Set<ProductExportDto> exportDtos = this.productRepository.getAllByBuyerIsNullAndPriceBetween(min, max)
                .stream()
                .map(p -> {
                    ProductExportDto exportDto = modelMapper.map(p, ProductExportDto.class);
                    exportDto.setSeller(p.getSeller().getFirstName() + " " + p.getSeller().getLastName());
                    return exportDto;
                })
                .collect(Collectors.toSet());
        try {
            FileWriter writer = new FileWriter(InOutPaths.TASK_01_OUT);
            this.gson.toJson(exportDtos, writer);
            writer.flush();
            writer.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    private void setRandomBuyer(Product product) {
        int id = this.random.nextInt((int) (this.userRepository.count() + 1));
        if (id != 0) {
            this.userRepository.findById((long) id).get().getBought().add(product);
        }
    }

    private void setRandomSeller(Product product) {
        int id = this.random.nextInt((int) (this.userRepository.count())) + 1;
        this.userRepository.findById((long) id).get().getSoldProducts().add(product);
    }

}
