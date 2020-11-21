package productshop.domain.dto.out.task02;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.NONE)
public class SetOfUsersWithSoldProductsDto {

    @XmlElement(name = "user")
    private Set<UserWithSoldProductsDto> users;

    public SetOfUsersWithSoldProductsDto() {
    }

    public Set<UserWithSoldProductsDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserWithSoldProductsDto> users) {
        this.users = users;
    }
}
