package cinema.client.service;

import cinema.client.entity.User;

public interface UserService {
    User registerNewUserAccount(User accountDto);

    User findByUsername(String username);

    User findByEmail(String email);
}
