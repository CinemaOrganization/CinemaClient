package cinema.client.service;

import cinema.client.entity.User;

public interface UserService {
    void registerNewUserAccount(User accountDto);

    User findByUsername(String username);

    User findByEmail(String email);
}
