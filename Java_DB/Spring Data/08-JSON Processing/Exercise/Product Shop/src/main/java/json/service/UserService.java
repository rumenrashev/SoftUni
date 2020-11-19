package json.service;

import java.io.IOException;

public interface UserService {

    public void seedUsers() throws IOException;

    public void getAllWithAtLeastOneSoldItem();

    public void selectAllUsersWithSoldItem();
}
