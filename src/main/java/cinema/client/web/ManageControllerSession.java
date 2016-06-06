package cinema.client.web;

import cinema.client.entity.*;
import cinema.client.service.*;
import cinema.client.web.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("manage/session")
public class ManageControllerSession {

    private SessionService sessionService;
    private FilmService filmService;
    private HallService hallService;
    private CinemaService cinemaService;
    private CommentService commentService;

    @Autowired
    public ManageControllerSession(SessionService sessionService, FilmService filmService, HallService hallService,
                                   CinemaService cinemaService,CommentService commentService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
        this.hallService = hallService;
        this.cinemaService = cinemaService;
        this.commentService = commentService;
    }

    @RequestMapping(method = GET)
    public String get(){
        return "manageSession";
    }

    @RequestMapping(value = "create",method = GET)
    public String create(Model model){

        List<Film> films = filmService.findAll();
        List<Hall> halls = hallService.findAll();
        Session session = new Session();
        LocalDate localDate = LocalDate.now();
        session.setDate(localDate);
        model.addAttribute(session);
        model.addAttribute("films",films);
        model.addAttribute("halls",halls);
        return "createSession";
    }

    @RequestMapping(value = "create",method = POST)
    public String create(@Valid Session session, BindingResult bindingResult,
                         Model model){

        if (bindingResult.hasErrors()){
            List<Film> films = filmService.findAll();
            List<Hall> halls = hallService.findAll();
            model.addAttribute("films",films);
            model.addAttribute("halls",halls);
            return "createSession";
        }
        Hall hall = hallService.findOne(session.getHall().getId());
        session.setCinema(hall.getCinema());
        sessionService.save(Arrays.asList(session));
        return "redirect:/manage/session";
    }

    @RequestMapping(value = "filmsForUp",method = GET)
    public String chooseFilmForUpSession(Model model){

        List<Film> films = filmService.findAll();
        model.addAttribute("films",films);
        return "filmsForUp";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "notfound";
    }


    @RequestMapping(value = "filmsForUp/sessions",method = GET)
    public String chooseSessionForUp(@RequestParam("film_id") long film_id,
                                @RequestParam(name = "strDate", defaultValue = "nearest") String date,
                                Model model){

        List<Session> sessions = sessionService.findByFilmAndDateOrderByCinemaAndHallAndTime(film_id,date);
        Set<Hall> halls = hallService.findBySessions(sessions);
        Set<Cinema> cinemas = cinemaService.findBySessions(sessions);
        Film requiredFilm = filmService.findOne(film_id);
        List<LocalDate> dates = sessionService.getAllSessionsByFilmDatesAsStrings(requiredFilm);

        model.addAttribute(sessions);
        model.addAttribute(halls);
        model.addAttribute(cinemas);
        model.addAttribute("dateList",dates);
        model.addAttribute("film", requiredFilm);
        return "sessionsForUpdate";
    }

    @RequestMapping(value = "filmsForUp/sessions/update",method = GET)
    public String updateSession(@RequestParam("session_id") long session_id,Model model){

        Session session = sessionService.findOne(session_id);
        List<Film> films = filmService.findAll();
        List<Hall> halls = hallService.findAll();

        model.addAttribute("films",films);
        model.addAttribute("halls",halls);
        model.addAttribute(session);
        return "updateSession";
    }

    @RequestMapping(value = "filmsForUp/sessions/update",method = POST)
    public String updateSession(@Valid Session session,BindingResult bindingResult,Model model){

        if (bindingResult.hasErrors()){

            List<Film> films = filmService.findAll();
            List<Hall> halls = hallService.findAll();
            model.addAttribute("films",films);
            model.addAttribute("halls",halls);
            return "updateSession";
        }
        Hall hall = hallService.findOne(session.getHall().getId());
        session.setCinema(hall.getCinema());
        sessionService.save(Arrays.asList(session));
        return "redirect:/manage/session";
    }

    @RequestMapping(value = "filmsForDel",method = GET)
    public String chooseFilmForDelSession(Model model){

        List<Film> films = filmService.findAll();
        model.addAttribute("films",films);
        return "filmsForDel";
    }

    @RequestMapping(value = "filmsForDel/sessions",method = GET)
    public String chooseSessionForDel(@RequestParam("film_id") long film_id,
                                     @RequestParam(name = "strDate", defaultValue = "nearest") String date,
                                     Model model){

        List<Session> sessions = sessionService.findByFilmAndDateOrderByCinemaAndHallAndTime(film_id,date);
        Set<Hall> halls = hallService.findBySessions(sessions);
        Set<Cinema> cinemas = cinemaService.findBySessions(sessions);
        Film requiredFilm = filmService.findOne(film_id);
        List<LocalDate> dates = sessionService.getAllSessionsByFilmDatesAsStrings(requiredFilm);

        model.addAttribute(sessions);
        model.addAttribute(halls);
        model.addAttribute(cinemas);
        model.addAttribute("dateList",dates);
        model.addAttribute("film", requiredFilm);

        return "sessionsForDelete";
    }

    @RequestMapping(value = "filmsForDel/sessions/delete",method = GET)
    public String deleteSession(@RequestParam("session_id") long session_id){

        Session session = sessionService.findOne(session_id);
        sessionService.delete(Arrays.asList(session));
        return "redirect:/manage/session";
    }
}
