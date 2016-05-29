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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("manage/session")
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
}
