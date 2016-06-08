package cinema.client.service;

import cinema.client.data.RoleRepository;
import cinema.client.data.UserRepository;
import cinema.client.entity.Role;
import cinema.client.entity.User;
import cinema.client.secure.exception.EmailExistsException;
import cinema.client.secure.exception.UsernameExistException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public User registerNewUserAccount(User user) {
        if (findByUsername(user.getUsername()) != null) {
            throw new UsernameExistException("Аккаунт с псевдонимом: " + user.getUsername()
                    + " уже существует:");
        }
        if (findByEmail(user.getEmail()) != null) {
            throw new EmailExistsException("Аккаунт с email адресом: " + user.getEmail()
                    + " уже существует:");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setMatchingPassword(user.getPassword());
        Role role = roleRepository.findByAuthority("ROLE_USER");
        user.setAuthorities(new HashSet<>(Arrays.asList(role)));
        user =  userRepository.save(user);
        logger.info("Создан новый пользователь " + user.getUsername());
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
