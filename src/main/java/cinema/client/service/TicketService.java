package cinema.client.service;

public interface TicketService {

    String getTicketsBySessionInJson(long session_id);

    void bookTickets(String tickets);
}
