package cinema.client.web;

import cinema.client.data.CinemaRepository;
import cinema.client.data.HallRepository;
import cinema.client.entity.Cinema;
import cinema.client.entity.Hall;
import cinema.client.service.CinemaService;
import cinema.client.service.HallService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/manage/hall")
public class ManageControllerHall {

    HallService hallService;
    CinemaService cinemaService;

    @Autowired
    public ManageControllerHall(HallService hallService,CinemaService cinemaService) {
        this.cinemaService = cinemaService;
        this.hallService = hallService;
    }

    @RequestMapping(value = "add")
    public String hallAdd(Model model){

        List<Cinema> cinemaList = cinemaService.findAll();
        model.addAttribute("cinemaList",cinemaList);
        return "HallAdd";
    }

    @RequestMapping(value = "/add/create")
    public String hallCreate(
            @RequestParam("hall_number") int hall_number,
            @RequestParam("cinema_id") long id,
            @RequestParam("rows") int rows,
            @RequestParam("numberInRow") int numInRow,
            @RequestParam("3d") boolean threeD){

        Cinema cinema = cinemaService.findOne(id);
        Hall hall = new Hall(hall_number,cinema,numInRow,rows,threeD);
        hallService.save(Arrays.asList(hall));
        return "manageHall";
    }


    @RequestMapping(value = "/choosedel")
    public String chooseDel(Model model){
        List<Hall> hallList = hallService.findAll();
        model.addAttribute("hallList",hallList);
        return "chooseDelHall";
    }

    @RequestMapping(value = "/choosedel/delete")
    public String delete(@RequestParam("hall_id")long id){
        hallService.delete(id);
        return "manageHall";
    }

    @RequestMapping(value = "/chooseup")
    public String chooseUp(Model model){
        List<Hall> hallList = hallService.findAll();
        model.addAttribute("hallList",hallList);
        return "chooseUpHall";
    }

    @RequestMapping(value = "/chooseup/change")
    public String hallChange(Model model,
                             @RequestParam("hall_id")long id){
        List<Cinema> cinemaList = cinemaService.findAll();
        model.addAttribute("cinemaList",cinemaList);
        Hall hall = hallService.findOne(id);
        model.addAttribute("hall",hall);
        return "hallChange";
    }
}
