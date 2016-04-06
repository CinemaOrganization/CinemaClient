package cinema.client.web;

import cinema.client.entity.Cinema;
import cinema.client.entity.Film;
import cinema.client.entity.Hall;
import cinema.client.entity.Session;
import cinema.client.service.CinemaService;
import cinema.client.service.FilmService;
import cinema.client.service.HallService;
import cinema.client.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/session")
public class SessionController {

    private CinemaService cinemaService;
    private FilmService filmService;
    private HallService hallService;
    private SessionService sessionService;

    @Autowired
    public SessionController(CinemaService cinemaService, FilmService filmService, HallService hallService, SessionService sessionService) {
        this.cinemaService = cinemaService;
        this.filmService = filmService;
        this.hallService = hallService;
        this.sessionService = sessionService;
    }

    public SessionController() {}

    @RequestMapping(method = GET)
    public String requireSessionByFilmAndDateAndOrderedByCinemaAndHallAndTime(
            @RequestParam("film_id") long film_id,
            @RequestParam(name = "strDate", defaultValue = "nearest") String date,
            Model model) {

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
        return "session";
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
