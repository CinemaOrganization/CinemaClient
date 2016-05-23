package cinema.client.data;

import cinema.client.entity.Session;
import cinema.client.entity.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

    List<Ticket> findBySession(Session session);
}
