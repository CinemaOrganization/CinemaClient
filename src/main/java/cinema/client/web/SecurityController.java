package cinema.client.web;


import cinema.client.entity.User;
import cinema.client.secure.exception.EmailExistsException;
import cinema.client.secure.exception.UsernameExistException;
import cinema.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class SecurityController {

    private UserService service;

    @Autowired
    public SecurityController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/user/logout", method = GET)
    public String showLogoutForm() {
        return "secure/logout";
    }

    @RequestMapping(value = "/user/registration", method = GET)
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "secure/registration";
    }

    @RequestMapping(value = "/user/registration", method = POST)
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid User account,
                                            BindingResult result) {
        if (!result.hasErrors()) {
            createUserAccount(account, result);
        }
        if (result.hasErrors()) {
            return new ModelAndView("secure/registration", "user", account);
        } else {
            return new ModelAndView("secure/successRegister", "user", account);
        }
    }

    private void createUserAccount(User accountDto, BindingResult result) {
        try {
            service.registerNewUserAccount(accountDto);
        } catch (UsernameExistException ex) {
            result.rejectValue("username", "registr.err.username");
        } catch (EmailExistsException ex) {
            result.rejectValue("email", "registr.err.email");
        }
    }
}
