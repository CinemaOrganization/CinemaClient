package cinema.client.service;

import cinema.client.entity.Ticket;

import java.util.List;

public interface TicketService {

    String getTicketsBySessionInJson(long session_id);

    void bookTickets(String tickets);

    List<Ticket> findByUsername(String username);
}
