package cinema.client.web;

import cinema.client.entity.Film;
import cinema.client.entity.Hall;
import cinema.client.entity.Session;
import cinema.client.service.FilmService;
import cinema.client.service.HallService;
import cinema.client.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "manage/session")
public class ManageControllerSession {

    private SessionService sessionService;
    private FilmService filmService;
    private HallService hallService;

    @Autowired
    public ManageControllerSession(SessionService sessionService, FilmService filmService, HallService hallService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
        this.hallService = hallService;
    }

    @RequestMapping
    public String get(){
        return "manageSession";
    }

    @RequestMapping(value = "add")
    public String add(Model model){

        List<Film> films = filmService.findAll();
        List<Hall> halls = hallService.findAll();
        model.addAttribute("films",films);
        model.addAttribute("halls",halls);
        return "sessionAdd";
    }

    @RequestMapping(value = "add/create")
    public String create(@RequestParam("film_id") long film_id,
                         @RequestParam("hall_id") long hall_id,
                         @RequestParam("cost") String cost,
                         @RequestParam("time")LocalTime time,
                         @RequestParam("date")LocalDate date){
        Film film = filmService.findOne(film_id);
        Hall hall = hallService.findOne(hall_id);
        date = LocalDate.of(1993,9,30);
        Session session = new Session(hall,film,hall.getCinema(),Double.parseDouble(cost),time,date);
        sessionService.save(Arrays.asList(session));
        return "manageSession";
    }
}
