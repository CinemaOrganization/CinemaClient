package cinema.client.web;

import cinema.client.entity.*;
import cinema.client.service.*;
import cinema.client.web.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/session")
public class SessionController {

    private CinemaService cinemaService;
    private FilmService filmService;
    private HallService hallService;
    private SessionService sessionService;
    private CommentService commentService;

    @Autowired
    public SessionController(CinemaService cinemaService
            ,FilmService filmService
            ,HallService hallService
            ,SessionService sessionService
            ,CommentService commentService) {
        this.cinemaService = cinemaService;
        this.filmService = filmService;
        this.hallService = hallService;
        this.sessionService = sessionService;
        this.commentService = commentService;
    }

    public SessionController() {}

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "notfound";
    }

    @RequestMapping(method = GET)
    public String requireSessionByFilmAndDateAndOrderedByCinemaAndHallAndTime(
            @RequestParam("film_id") long film_id,
            @RequestParam(name = "strDate", defaultValue = "nearest") String date,
            Model model) {

        fillModel(film_id, date, new Comment(), model);
        return "session";
    }


    @RequestMapping(method = POST)
    public String registerUserAccount(@ModelAttribute("comment") @Valid Comment comment,
                                      BindingResult result, Model model) {
        if (!result.hasErrors()) {
            commentService.addNewComment(comment);
        }
        fillModel(comment.getFilm().getId(), "nearest", comment, model);
        return "session";
    }

    private void fillModel(long film_id, String date, Comment comment, Model model) {
        List<Session> sessions = sessionService.findByFilmAndDateOrderByCinemaAndHallAndTime(film_id, date);
        Set<Hall> halls = hallService.findBySessions(sessions);
        Set<Cinema> cinemas = cinemaService.findBySessions(sessions);
        Film requiredFilm = filmService.findOne(film_id);
        List<LocalDate> dates = sessionService.getAllSessionsByFilmDatesAsStrings(requiredFilm);

        model.addAttribute(sessions);
        model.addAttribute(halls);
        model.addAttribute(cinemas);
        model.addAttribute("dateList",dates);
        model.addAttribute("film", requiredFilm);
        model.addAttribute("comment", comment);
    }

    @RequestMapping(value = "/add",method = GET)
    public String addData() {

        sessionService.add();

        return "session";
    }
    @RequestMapping(value = "/delete",method = GET)
    public String deleteData() {

        sessionService.deteteData();
        return "session";
    }


}
