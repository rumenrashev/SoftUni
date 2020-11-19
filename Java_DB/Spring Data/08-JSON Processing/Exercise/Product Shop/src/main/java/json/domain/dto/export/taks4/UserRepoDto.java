package json.domain.dto.export.taks4;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class UserRepoDto {

    @Expose
    private long count;

    @Expose
    private List<UserViewDto> users = new ArrayList<>();

    public UserRepoDto() {
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<UserViewDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserViewDto> users) {
        this.users = users;
    }
}
