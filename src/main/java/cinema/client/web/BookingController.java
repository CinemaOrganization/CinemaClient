package cinema.client.web;

import cinema.client.entity.Hall;
import cinema.client.service.HallService;
import cinema.client.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private HallService hallService;
    private TicketService ticketService;

    @Autowired
    public BookingController(HallService hallService, TicketService ticketService) {
        this.hallService = hallService;
        this.ticketService = ticketService;
    }

    @RequestMapping(method = GET)
    public String requireBookingBySession(
            @RequestParam("session_id") long session_id, Model model) {

        Hall currentHall = hallService.getHallBySession(session_id);
        String ticketList = ticketService.getTicketsBySessionInJson(session_id);

        model.addAttribute("hall", currentHall);
        model.addAttribute("paidTickets", ticketList);
        return "booking";
    }
}
