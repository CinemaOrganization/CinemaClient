package cinema.client.service;

import cinema.client.component.JsonTicketConverter;
import cinema.client.data.TicketRepository;
import cinema.client.entity.Session;
import cinema.client.entity.Ticket;
import cinema.client.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private SessionService sessionService;
    private JsonTicketConverter jsonTicketConverter;
    private UserService userService;
    static Logger logger = Logger.getLogger(TicketServiceImpl.class);

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository,
                             SessionService sessionService,
                             JsonTicketConverter jsonTicketConverter,
                             UserService userService) {
        this.ticketRepository = ticketRepository;
        this.sessionService = sessionService;
        this.jsonTicketConverter = jsonTicketConverter;
        this.userService = userService;
    }

    @Override
    public String getTicketsBySessionInJson(long session_id) {
        Session session = sessionService.findOne(session_id);
        List<Ticket> tickets = ticketRepository.findBySession(session);
        String ticketsInJson = jsonTicketConverter.objectsToJson(tickets);
        return ticketsInJson;
    }

    @Transactional
    @Override
    public void bookTickets(String ticketsJson) {
        List<Ticket> tickets = jsonTicketConverter.toObjects(ticketsJson);
        tickets = fillTickets(tickets);
        ticketRepository.save(tickets);
        for (Ticket ticket : tickets){
            logger.info("Забронирован билет " + ticket);
        }
    }

    @Override
    public List<Ticket> findByUsername(String username) {
        User user = userService.findByUsername(username);
        return ticketRepository.findByUser(user);
    }

    @Override
    public void removeTicket(long id) {

        ticketRepository.delete(id);
        logger.info("Удалён билет с ИД = " + id);
    }

    private List<Ticket> fillTickets(List<Ticket> tickets) {
        Session session = sessionService.findOne(tickets.get(0).getSession().getId());
        User user = userService.findByUsername(tickets.get(0).getUser().getUsername());
        tickets.forEach(ticket -> {
            ticket.setSession(session);
            ticket.setFilm(session.getFilm());
            ticket.setCinema(session.getCinema());
            ticket.setHall(session.getHall());
            ticket.setUser(user);
        });

        return tickets;
    }
}
