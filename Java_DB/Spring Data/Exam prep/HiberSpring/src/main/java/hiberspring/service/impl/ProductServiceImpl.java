package hiberspring.service.impl;

import hiberspring.domain.dtos.xml.ProductsXmlRootDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.service.ProductService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;

import static hiberspring.common.Constants.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository,
                              FileUtil fileUtil, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean productsAreImported() {
        return productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return this.fileUtil.readFile(PRODUCTS_PATH);
    }

    @Override
    public String importProducts() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        this.xmlParser.parseXml(ProductsXmlRootDto.class, PRODUCTS_PATH)
                .getDtos()
                .forEach(e->{
                    Optional<Branch> branch = this.branchRepository.findByName(e.getBranch());
                    if (branch.isPresent() && this.validationUtil.isValid(e)){
                        Product product = this.modelMapper.map(e, Product.class);
                        product.setBranch(branch.get());
                        this.productRepository.saveAndFlush(product);
                        sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,PRODUCT,e.getName()));
                    }else {
                        sb.append(INCORRECT_DATA_MESSAGE);
                    }
                    sb.append(System.lineSeparator());
                });
        return sb.toString();
    }
}
