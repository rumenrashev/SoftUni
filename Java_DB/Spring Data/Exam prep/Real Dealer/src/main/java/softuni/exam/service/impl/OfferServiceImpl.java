package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.in.OfferImportDto;
import softuni.exam.models.dtos.in.OffersRootImportDto;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Offer;
import softuni.exam.models.entities.Picture;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

@Service
public class OfferServiceImpl implements OfferService {

    private static final String OFFERS_IN_PATH = "src/main/resources/files/xml/offers.xml";

    private final OfferRepository offerRepository;
    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public OfferServiceImpl(OfferRepository offerRepository, CarRepository carRepository, SellerRepository sellerRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.offerRepository = offerRepository;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(OFFERS_IN_PATH)));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        OffersRootImportDto offersRootImportDto = this.xmlParser.fromXML(OffersRootImportDto.class, OFFERS_IN_PATH);
        List<OfferImportDto> offerImportDtos = offersRootImportDto.getOfferImportDtos();
        for (OfferImportDto offerImportDto : offerImportDtos) {
            if (validationUtil.isValid(offerImportDto)){
                Offer offer = this.modelMapper.map(offerImportDto, Offer.class);
                Car car = carRepository.findById(offerImportDto.getCar().getId()).orElse(null);
                if (car != null){
                    Set<Picture> pictures = car.getPictures();
                    offer.setPictures(pictures);
                }
                Seller seller = sellerRepository.findById(offerImportDto.getSeller().getId()).orElse(null);
                offer.setCar(car);
                offer.setSeller(seller);
                this.offerRepository.saveAndFlush(offer);
                sb.append(String.format("Successfully import offer %s - %s",
                        offer.getAddedOn().toString()
                        ,offer.getHasGoldStatus()))
                        .append(System.lineSeparator());
            }else {
                sb.append("Invalid offer").append(System.lineSeparator());
            }

        }
        return sb.toString();
    }
}
