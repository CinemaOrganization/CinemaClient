package cinema.client.service;

import cinema.client.data.RoleRepository;
import cinema.client.data.UserRepository;
import cinema.client.entity.Role;
import cinema.client.entity.User;
import cinema.client.secure.exception.EmailExistsException;
import cinema.client.secure.exception.UsernameExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public void registerNewUserAccount(User user) {
        if (usernameExist(user.getUsername())) {
            throw new UsernameExistException("Аккаунт с псевдонимом: " + user.getUsername()
                    + " уже существует:");
        }
        if (emailExist(user.getEmail())) {
            throw new EmailExistsException("Аккаунт с email адресом: " + user.getEmail()
                    + " уже существует:");
        }

        Role role = roleRepository.findByAuthority("ROLE_USER");
        user.setAuthorities(new HashSet<>(Arrays.asList(role)));
        userRepository.save(user);
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
    private boolean usernameExist(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }
}
