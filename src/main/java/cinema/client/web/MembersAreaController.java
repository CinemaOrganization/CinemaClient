package cinema.client.web;

import cinema.client.entity.Ticket;
import cinema.client.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    String enteringInMemberArea(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        List<Ticket> tickets = ticketService.findByUsername(name);
        model.addAttribute("ticketList",tickets);
        return "userArea";
    }

    @RequestMapping(params = "remove", method = POST)
    public String removeComment(@RequestParam("id") long id, Model model) {
        ticketService.removeTicket(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        List<Ticket> tickets = ticketService.findByUsername(name);
        model.addAttribute("ticketList",tickets);
        return "userArea";
    }
}
