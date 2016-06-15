package cinema.client.web;

import cinema.client.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class HomeController {

    private FilmService filmService;

    @Autowired
    public HomeController(FilmService filmService) {
        this.filmService = filmService;
    }

    @RequestMapping(method = GET)
    public String selectFilmsByDates(@RequestParam(value = "date1", defaultValue = "now") String date1,
                                     @RequestParam(value = "date2", defaultValue = "empty") String date2,
                                     Model model) {
        model.addAttribute("filmList",
                filmService.findByDates(date1,date2));
        return "home";
    }

}
