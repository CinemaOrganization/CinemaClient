package cinema.client.web;


import cinema.client.entity.Cinema;
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
import java.util.List;

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

    @RequestMapping(value = "/choosedel",method = GET)
    public String chooseDel(Model model){
        List<Hall> hallList = hallService.findAll();
        model.addAttribute("hallList",hallList);
        return "chooseDelHall";
    }

    @RequestMapping(value = "/choosedel/delete",method = POST)
    public String delete(@RequestParam("hall_id")long id){
        hallService.delete(id);
        return "manageHall";
    }

    @RequestMapping(value = "/chooseup",method = GET)
    public String chooseUp(Model model){
        List<Hall> hallList = hallService.findAll();
        model.addAttribute("hallList",hallList);
        return "chooseUpHall";
    }

    @RequestMapping(value = "/chooseup/update", method = GET)
    public String updateHall(Model model,
                             @RequestParam("hall_id")long id){
        List<Cinema> cinemaList = cinemaService.findAll();
        model.addAttribute("cinemaList",cinemaList);
        Hall hall = hallService.findOne(id);
        model.addAttribute("hall",hall);
        return "updateHall";
    }

    @RequestMapping(value = "chooseup/update",method = POST)
    public String updateHall(@Valid Hall hall, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "updateHall";
        }
        hallService.save(Arrays.asList(hall));
        return "redirect:/manage/hall";
    }
}
