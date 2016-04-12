package cinema.client.web;

import cinema.client.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

    private FilmService filmService;

    @Autowired
    public HomeController(
            FilmService filmRepository) {
        this.filmService = filmRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute(
                filmService.findAll());
        return "home";
    }

}
