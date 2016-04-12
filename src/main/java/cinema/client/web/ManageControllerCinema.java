package cinema.client.web;

import cinema.client.data.CinemaRepository;
import cinema.client.entity.Cinema;
import cinema.client.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Controller
@RequestMapping("manage/cinema")
public class ManageControllerCinema {

    CinemaService cinemaService;

    @Autowired
    public ManageControllerCinema(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @RequestMapping(value = "add",method = GET)
    public String cinemaAdd(){
        return "cinemaAdd";
    }

    @RequestMapping(value = "add/create",method = POST)
    public String cinemaCreate(@RequestParam("name") String name){

        Cinema cinema = new Cinema(name);
        cinemaService.saveCinemas(Arrays.asList(cinema));
        return "manageCinema";
    }

    @RequestMapping(value = "chooseup", method = GET)
    public String cinemaChoose(Model model){
        List<Cinema> cinemaList = cinemaService.findAll();
        model.addAttribute("cinemaList",cinemaList);
        return "chooseUpCinema";
    }

    @RequestMapping(value = "chooseup/change",method = GET)
    public String cinemaChangeUp(
            @RequestParam("cinema_id") long id,
            Model model){
        Cinema cinema = cinemaService.findOne(id);
        model.addAttribute("cinema",cinema);
        return "cinemaChange";
    }

    @RequestMapping(value = "chooseup/change/update",method = POST)
    public String cinemaUpdate(
            @RequestParam("cinema_id") long id,
            @RequestParam("cinema_name") String name){
        Cinema cinema = cinemaService.findOne(id);
        cinema.setName(name);
        cinemaService.saveCinemas(Arrays.asList(cinema));
        return "manageCinema";
    }

    @RequestMapping(value = "choosedel", method = GET)
    public String cinemaChooseDel(Model model){
        List<Cinema> cinemaList = cinemaService.findAll();
        model.addAttribute("cinemaList",cinemaList);
        return "chooseDelCinema";
    }

    @RequestMapping(value = "choosedel/delete", method = GET)
    public String cinemaDelete(@RequestParam("cinema_id") long id){
        cinemaService.delete(id);
        return "manageCinema";
    }
}
