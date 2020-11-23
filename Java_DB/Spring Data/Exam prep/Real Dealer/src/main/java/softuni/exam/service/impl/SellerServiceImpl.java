package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.in.SellerImportDto;
import softuni.exam.models.dtos.in.SellersRootImportDto;
import softuni.exam.models.entities.Seller;
import softuni.exam.models.enums.Rating;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

@Service
public class SellerServiceImpl implements SellerService {

    private static final String SELLERS_IN_PATH = "src/main/resources/files/xml/sellers.xml";

    private final SellerRepository sellerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public SellerServiceImpl(SellerRepository sellerRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.sellerRepository = sellerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return String.join("",Files.readAllLines(Path.of(SELLERS_IN_PATH)));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        SellersRootImportDto sellersRootImportDto = this.xmlParser.fromXML(SellersRootImportDto.class, SELLERS_IN_PATH);
        List<SellerImportDto> sellerImportDtos = sellersRootImportDto.getSellerImportDtos();
        for (SellerImportDto sellerImportDto : sellerImportDtos) {
            Rating rating = null;
            try {
               rating = Rating.valueOf(sellerImportDto.getRating());
            }catch (Exception e){

            }
            if (rating != null && validationUtil.isValid(sellerImportDto)){
                Seller seller = this.modelMapper.map(sellerImportDto, Seller.class);
                seller.setRating(rating);
                this.sellerRepository.saveAndFlush(seller);
                sb.append(String.format("Successfully import seller %s - %s",
                        seller.getLastName(),seller.getEmail()))
                        .append(System.lineSeparator());
            }else {
                sb.append("Invalid seller").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
