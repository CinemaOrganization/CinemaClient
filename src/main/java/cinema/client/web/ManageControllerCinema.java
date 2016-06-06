package cinema.client.web;

import cinema.client.entity.Cinema;
import cinema.client.service.CinemaService;
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
@RequestMapping("manage/cinema")
public class ManageControllerCinema {

    CinemaService cinemaService;

    @Autowired
    public ManageControllerCinema(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @RequestMapping(method = GET)
    public String get(){
        return "manageCinema";
    }

    @RequestMapping(value = "create",method = GET)
    public String cinemaCreate(Model model){
        Cinema cinema = new Cinema();
        model.addAttribute(cinema);
        return "cinemaCreate";
    }

    @RequestMapping(value = "create",method = POST)
    public String cinemaCreate(@Valid Cinema cinema, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "cinemaCreate";
        }
        cinemaService.saveCinemas(Arrays.asList(cinema));
        return "redirect:/manage/cinema";
    }

    @RequestMapping(value = "chooseup", method = GET)
    public String cinemaChoose(Model model){
        List<Cinema> cinemaList = cinemaService.findAll();
        model.addAttribute("cinemaList",cinemaList);
        return "chooseUpCinema";
    }

    @RequestMapping(value = "chooseup/update",method = GET)
    public String cinemaUpdate(
            @RequestParam("cinema_id") long id,
            Model model){
        Cinema cinema = cinemaService.findOne(id);
        model.addAttribute(cinema);
        return "cinemaUpdate";
    }

    @RequestMapping(value = "chooseup/update",method = POST)
    public String cinemaUpdate(@Valid Cinema cinema,BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "cinemaUpdate";
        }
        cinemaService.saveCinemas(Arrays.asList(cinema));
        return "redirect:/manage/cinema";
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
