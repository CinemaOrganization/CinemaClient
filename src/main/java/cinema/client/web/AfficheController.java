package cinema.client.web;

import cinema.client.data.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/affiche")
public class AfficheController {
    private FilmRepository filmRepository;

    @Autowired
    public AfficheController(
            FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String affiche(Model model) {
        model.addAttribute(
                filmRepository.findFilms(20));
        return "affiche";
    }
}