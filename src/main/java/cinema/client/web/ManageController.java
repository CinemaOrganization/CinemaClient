package cinema.client.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("manage")
public class ManageController {

    @RequestMapping(method = GET)
    public String manage(){
        return "manage";
    }

}
