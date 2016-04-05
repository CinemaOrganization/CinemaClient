package cinema.client.web;

import cinema.client.entity.Cinema;
import cinema.client.entity.Hall;
import cinema.client.entity.Session;
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

    private SessionService sessionService;
    private LocalDate date = LocalDate.now();

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @RequestMapping(method = GET)
    public String requireSessionByFilmAndDateAndOrderedByCinemaAndHallAndTime(
            @RequestParam("film_id") long film_id,
            @RequestParam(name = "strDate", defaultValue = "today") String date,
            Model model) {

        List<Session> sessions = sessionService.findByFilmAndDateOrderByCinemaAndHallAndTime(film_id, date);
        Set<Hall> halls = sessionService.getHallsBySessions(sessions);
        Set<Cinema> cinemas = sessionService.getCinemasBySessions(sessions);
        model.addAttribute(sessions);
        model.addAttribute(halls);
        model.addAttribute(cinemas);
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
