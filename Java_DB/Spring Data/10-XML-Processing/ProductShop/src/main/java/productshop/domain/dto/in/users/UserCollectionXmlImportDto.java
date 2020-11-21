package productshop.domain.dto.in.users;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.NONE)
public class UserCollectionXmlImportDto {

    @XmlElement(name = "user")
    private List<UserXmlImportDto> users;

    public UserCollectionXmlImportDto() {
    }

    public List<UserXmlImportDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserXmlImportDto> users) {
        this.users = users;
    }
}
