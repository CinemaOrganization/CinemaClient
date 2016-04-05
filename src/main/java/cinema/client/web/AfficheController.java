package cinema.client.web;

import cinema.client.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AfficheController {

    private FilmService filmService;

    @Autowired
    public AfficheController(
            FilmService filmRepository) {
        this.filmService = filmRepository;
    }

    @RequestMapping(value = "/affiche", method = RequestMethod.GET)
    public String affiche(Model model) {
        model.addAttribute(
                filmService.findAll());
        return "affiche";
    }

}