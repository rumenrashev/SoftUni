package softuni.exam.models.dtos.in;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.NONE)
public class OffersRootImportDto {

    @XmlElement(name = "offer")
    private List<OfferImportDto> offerImportDtos;

    public OffersRootImportDto() {
    }

    public List<OfferImportDto> getOfferImportDtos() {
        return offerImportDtos;
    }

    public void setOfferImportDtos(List<OfferImportDto> offerImportDtos) {
        this.offerImportDtos = offerImportDtos;
    }
}
