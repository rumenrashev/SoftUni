package softuni.exam.models.dtos.in;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.NONE)
public class SellersRootImportDto {

    @XmlElement(name = "seller")
    private List<SellerImportDto> sellerImportDtos;

    public SellersRootImportDto() {
    }

    public List<SellerImportDto> getSellerImportDtos() {
        return sellerImportDtos;
    }

    public void setSellerImportDtos(List<SellerImportDto> sellerImportDtos) {
        this.sellerImportDtos = sellerImportDtos;
    }
}
