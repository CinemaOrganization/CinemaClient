package cinema.client.web;

import cinema.client.entity.*;
import cinema.client.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
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


    @RequestMapping(method = GET)
    public String requireSessionByFilmAndDateAndOrderedByCinemaAndHallAndTime(
            @RequestParam("film_id") long film_id,
            @RequestParam(name = "strDate", defaultValue = "nearest") String date,
            Model model) {

        fillModel(film_id, date, new Comment(), model);
        return "session";
    }


    @RequestMapping(params = "add", method = POST)
    public String addNewComment(@ModelAttribute("comment") @Valid Comment comment,
                                BindingResult result, Model model) {
        if (!result.hasErrors()) {
            commentService.addNewComment(comment);
        }
        if (result.hasErrors()) {
            fillModel(comment.getFilm().getId(), "nearest", comment, model);
            return "session";
        }
        fillModel(comment.getFilm().getId(), "nearest", new Comment(), model);
        return "session";
    }

    @RequestMapping(params = "remove", method = POST)
    public String removeComment(@ModelAttribute("comment") @Valid Comment comment,
                                BindingResult result, Model model) {
        commentService.removeComment(comment.getId());
        if (result.hasErrors()) {
            fillModel(comment.getFilm().getId(), "nearest", comment, model);
            return "session";
        }
        fillModel(comment.getFilm().getId(), "nearest", new Comment(), model);
        return "session";
    }

    private void fillModel(long film_id, String date, Comment comment, Model model) {
        List<Session> sessions = sessionService.findByFilmAndDateOrderByCinemaAndHallAndTime(film_id, date);
        Set<Hall> halls = hallService.findBySessions(sessions);
        Set<Cinema> cinemas = cinemaService.findBySessions(sessions);
        Film requiredFilm = filmService.findOne(film_id);
        List<LocalDate> dates = sessionService.getAllSessionsByFilmDatesAsStrings(requiredFilm);
        List<Comment> comments = commentService.findByFilmAndOrderByTime(film_id);

        Map<Hall, Integer> collect = sessions.stream().collect(groupingBy(s -> s.getHall()
                , collectingAndThen(summingInt(s -> 1), Function.identity())));
        OptionalInt max = collect.values().stream().mapToInt(v -> v).max();
        if (max.isPresent()){
            model.addAttribute("maxCol",max.getAsInt());
        }

        model.addAttribute(sessions);
        model.addAttribute(halls);
        model.addAttribute(cinemas);
        model.addAttribute("dateList",dates);
        model.addAttribute("film", requiredFilm);
        model.addAttribute(comments);
        model.addAttribute(comment);
    }

}
