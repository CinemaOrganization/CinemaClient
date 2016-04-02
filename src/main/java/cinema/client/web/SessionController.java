package cinema.client.web;

import cinema.client.data.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class SessionController {
    private SessionRepository sessionRepository;

    @Autowired
    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @RequestMapping(value = "/session", method = GET)
    public String requireSessionByFilmAndCinema(@RequestParam("film_id") long film_id,
                                                @RequestParam(value = "cinema_id",defaultValue ="0") long cinema_id,
                                                Model model) {
        model.addAttribute(sessionRepository.findByCinemaAndFilmLike(film_id,cinema_id));
        return "session";
    }
}
