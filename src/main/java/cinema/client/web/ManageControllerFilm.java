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

    @RequestMapping(method = GET)
    public String get(){
        return "manageFilm";
    }

    @RequestMapping(value = "create",method = GET)
    public String createFilm(Model model)
    {
        model.addAttribute(new Film());
        return "createFilm";
    }

    @RequestMapping(value = "create",method = POST)
    public String createFilm(@Valid Film film, BindingResult bindingResult, Model model)
    {
        boolean isExistedFilm = filmService.isExistedFilm(film);
        if (bindingResult.hasErrors() || isExistedFilm){
            if (isExistedFilm){
                model.addAttribute("filmExistError","filmExistError");
            }
            return "createFilm";
        }
        filmService.saveFilms(Arrays.asList(film));
        return "redirect:/manage/film";
    }

    @RequestMapping(value = "chooseup/update",method = POST)
    public String filmUpdate(@Valid Film film, BindingResult bindingResult,Model model){

        boolean isAnotherExistedFilm = filmService.isAnotherExistedFilm(film);
        if (bindingResult.hasErrors() || isAnotherExistedFilm){
            if (isAnotherExistedFilm){
                model.addAttribute("filmExistError","filmExistError");
            }
            return "filmUpdate";
        }
        filmService.saveFilms(Arrays.asList(film));
        return "redirect:/manage/film";
    }

    @RequestMapping(value = "chooseup/update",method = GET)
    public String filmUpdate(
            @RequestParam("film_id") long id,
            Model model){
        Film film = filmService.findOne(id);
        model.addAttribute(film);
        return "filmUpdate";
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
        return "redirect:/manage/film";
    }
}
