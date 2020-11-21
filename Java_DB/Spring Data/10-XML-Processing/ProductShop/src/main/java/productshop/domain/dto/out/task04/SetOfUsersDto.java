package productshop.domain.dto.out.task04;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.NONE)
public class SetOfUsersDto {

    @XmlElement(name = "user")
    private Set<UserDto> users;

    @XmlAttribute(name = "count")
    private int count;

    public SetOfUsersDto() {
    }

    public Set<UserDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDto> users) {
        this.users = users;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
