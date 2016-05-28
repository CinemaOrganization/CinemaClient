package cinema.client.web;


import cinema.client.entity.Cinema;
import cinema.client.entity.Film;
import cinema.client.entity.Hall;
import cinema.client.service.CinemaService;
import cinema.client.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/manage/hall")
public class ManageControllerHall {

    HallService hallService;
    CinemaService cinemaService;

    @Autowired
    public ManageControllerHall(HallService hallService,CinemaService cinemaService) {
        this.cinemaService = cinemaService;
        this.hallService = hallService;
    }

    @RequestMapping(method = GET)
    public String get(){
        return "manageHall";
    }


    @RequestMapping(value = "create",method = GET)
    public String hallCreate(Model model){

        Hall hall = new Hall();
        List<Cinema> cinemaList = cinemaService.findAll();

        Map<Long,String> map = new HashMap<Long, String>();

        for (Cinema cinema: cinemaList) {
            map.put(cinema.getId(),cinema.getName());

        }
        model.addAttribute(hall);
        model.addAttribute("cinemaList",cinemaList);
        return "createHall";
    }

    @RequestMapping(value = "create",method = POST)
    public String hallCreate(@Valid Hall hall , BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "createHall";
        }
        hallService.save(Arrays.asList(hall));
        return "redirect:/manage/hall";
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

    @RequestMapping(value = "chooseup/change/update")
    public String hallUpdate( @RequestParam("hall_id") long hall_id,
                              @RequestParam("hall_number") int number,
                              @RequestParam("rows") int rows,
                              @RequestParam("numberInRow") int numberInRow,
                              @RequestParam("3d") boolean threeD,
                              @RequestParam("cinema_id") long cinema_id){

        Cinema cinema = cinemaService.findOne(cinema_id);
        Hall hall = hallService.findOne(hall_id);
        hall.setCinema(cinema);
        hall.setNumber(number);
        hall.setNumberInRows(numberInRow);
        hall.setRows(rows);
        hall.setThreeD(threeD);
        hallService.save(Arrays.asList(hall));
        return "manageHall";
    }
}
