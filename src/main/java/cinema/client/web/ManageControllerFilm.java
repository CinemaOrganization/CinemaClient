package cinema.client.web;

import cinema.client.entity.Film;
import cinema.client.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("manage/film")
public class ManageControllerFilm {

    private FilmService filmService;

    @Autowired
    public ManageControllerFilm(FilmService filmService) {
        this.filmService = filmService;
    }

    @RequestMapping(value = "create",method = GET)
    public String createFilm(Model model)
    {
        model.addAttribute(new Film());
        return "createFilm";
    }

    @RequestMapping(value = "create",method = POST)
    public String checkBeforeCreate(@Valid Film film, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()){
            return "createFilm";
        }
        filmService.saveFilms(Arrays.asList(film));
        return "redirect:/manage/film";
    }

    @RequestMapping(value = "chooseup/change/update",method = POST)
    public String filmUpdate(
            @RequestParam("film_id") long id,
            @RequestParam("name") String name,
            @RequestParam("studio") String studio,
            @RequestParam("duration") LocalTime duration,
            @RequestParam("year") String year,
            @RequestParam("description") String description){
        Film film = filmService.findOne(id);
        film.setName(name);
        film.setStudio(studio);
        film.setDuration(duration);
        film.setYear(Integer.parseInt(year));
        film.setDescription(description);
        filmService.saveFilms(Arrays.asList(film));
        return "manageFilm";
    }

    @RequestMapping(value = "chooseup/change",method = GET)
    public String filmChange(
            @RequestParam("film_id") long id,
            Model model){
        Film film = filmService.findOne(id);
        model.addAttribute("film",film);
        return "filmChange";
    }

    @RequestMapping(value = "chooseup",method = GET)
    public String chooseFilm(Model model){

        List<Film> filmList = filmService.findAll();
        model.addAttribute("filmList",filmList);
        return "chooseUpFilm";
    }

    @RequestMapping(value = "choosedel",method = GET)
    public String choseFilmForDel(Model model){
        List<Film> filmList = filmService.findAll();
        model.addAttribute("filmList",filmList);
        return "chooseDelFilm";
    }

    @RequestMapping(value = "choosedel/delete",method = GET)
    public String deleteFilm(@RequestParam("film_id") long id){
        filmService.deleteFilm(id);
        return "manageFilm";
    }
}
