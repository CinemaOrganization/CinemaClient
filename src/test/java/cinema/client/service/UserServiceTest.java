package cinema.client.service;

import cinema.client.SetupData.SetupData;
import cinema.client.data.RoleRepository;
import cinema.client.data.UserRepository;
import cinema.client.entity.Role;
import cinema.client.entity.User;
import cinema.client.secure.exception.EmailExistsException;
import cinema.client.secure.exception.UsernameExistException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SecurityTestConfig.class})
public class UserServiceTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    SetupData setupData = new SetupData();

    @Before
    public void setUp() throws Exception {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
    }

//    @Test
//    public void userService_shouldRegisterAccaunt() {
//        User expectedUser = setupData.getUsers().get(0);
//        Role role = setupData.getRoles().get(0);
//
//        when(userRepository.findByUsername(expectedUser.getUsername()))
//                .thenReturn(null);
//        when(userRepository.findByEmail(expectedUser.getEmail()))
//                .thenReturn(null);
//        when(roleRepository.findByAuthority("ROLE_USER"))
//                .thenReturn(role);
//        when(userRepository.save(expectedUser))
//                .thenReturn(expectedUser);
//
//        UserService userService = new UserServiceImpl(userRepository, roleRepository);
//        User user = userService.registerNewUserAccount(expectedUser);
//
//        assertNotNull(user);
//        assertEquals(expectedUser, user);
//
//    }

    @Test(expected = UsernameExistException.class)
    public void userService_CantRegisterAccauntAndThrowExceptionUsernameExist() {
        User expectedUser = setupData.getUsers().get(0);
        Role role = setupData.getRoles().get(0);

        when(userRepository.findByUsername(expectedUser.getUsername()))
                .thenReturn(expectedUser);
        when(userRepository.findByEmail(expectedUser.getEmail()))
                .thenReturn(null);

        UserService userService = new UserServiceImpl(userRepository, roleRepository);
        userService.registerNewUserAccount(expectedUser);

    }

    @Test(expected = EmailExistsException.class)
    public void userService_CantRegisterAccauntAndThrowExceptionEmailExist() {
        User expectedUser = setupData.getUsers().get(0);
        Role role = setupData.getRoles().get(0);

        when(userRepository.findByUsername(expectedUser.getUsername()))
                .thenReturn(null);
        when(userRepository.findByEmail(expectedUser.getEmail()))
                .thenReturn(expectedUser);

        UserService userService = new UserServiceImpl(userRepository, roleRepository);
        userService.registerNewUserAccount(expectedUser);

    }

}
