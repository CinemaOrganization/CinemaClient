package cinema.client.web;

import cinema.client.entity.Ticket;
import cinema.client.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/user/my")
public class MembersAreaController {

    private TicketService ticketService;

    @Autowired
    public MembersAreaController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(method = POST)
    String enteringInMemberArea(@RequestParam(name = "username") String username, Model model) {
        List<Ticket> tickets = ticketService.findByUsername(username);
        model.addAttribute("ticketList",tickets);
        return "userArea";
    }
}
