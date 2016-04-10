package cinema.client.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "manage")
public class ManageController {

    @RequestMapping(method = GET)
    public String manage(){
        return "manage";
    }

    @RequestMapping(value = "film",method = GET)
    public String manageFilm(){
        return "manageFilm";
    }

    @RequestMapping(value = "hall",method = GET)
    public String manageHall(){
        return "manageHall";
    }

    @RequestMapping(value = "cinema",method = GET)
    public String manageCinema(){
        return "manageCinema";
    }

    @RequestMapping(value = "session",method = GET)
    public String manageSession(){
        return "manageSession";
    }
}
