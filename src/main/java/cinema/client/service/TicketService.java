package cinema.client.service;

import cinema.client.entity.Ticket;

import java.util.Iterator;
import java.util.List;

public interface TicketService {

    String getTicketsBySessionInJson(long session_id);

    boolean bookTickets(String tickets);

    List<Ticket> findByUsername(String username);

    void removeTicket(long id);

    Iterable<Ticket> findAll();

    Ticket findOne(long id);

    void save(Ticket ticket);
}
