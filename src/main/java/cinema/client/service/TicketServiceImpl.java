package cinema.client.service;

import cinema.client.component.JsonTicketConverter;
import cinema.client.data.TicketRepository;
import cinema.client.entity.Session;
import cinema.client.entity.Ticket;
import cinema.client.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private SessionService sessionService;
    private JsonTicketConverter jsonTicketConverter;
    private UserService userService;
    static Logger logger = Logger.getLogger(TicketServiceImpl.class);
    private ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

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

    @Override
    public Ticket findOne(long id) {
        return ticketRepository.findOne(id);
    }

    @Transactional
    @Override
    public boolean bookTickets(String ticketsJson) {
        List<Ticket> tickets = jsonTicketConverter.toObjects(ticketsJson);
        tickets = fillTickets(tickets);
        Ticket anyTicket = tickets.get(0);
        LocalDate date = anyTicket.getSession().getDate();
        LocalTime time = anyTicket.getSession().getTime();
        LocalDateTime localDateTime = LocalDateTime.of(date.getYear(),date.getMonth(),
                                                            date.getDayOfMonth(),time.getHour(),
                                                            time.getMinute());

        long  unbookTime = Duration.between(LocalDateTime.now(),localDateTime).toMinutes() - 20;
        if (unbookTime <0){
            return false;
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (Ticket ticket : tickets) {
            for (GrantedAuthority grantedAuthority : auth.getAuthorities()){
                if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                    ticket.setAccepted(true);
                }
            }
            ticketRepository.save(ticket);
            service.schedule(() ->{
                Ticket one = ticketRepository.findOne(ticket.getId());
                if (!one.isAccepted()){
                    ticketRepository.delete(ticket.getId());
                }
            },unbookTime, TimeUnit.MINUTES);
            logger.info("Забронирован билет " + ticket);
        }
        return true;
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

    @Override
    public Iterable<Ticket> findAll() {
        return ticketRepository.findAll();
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
