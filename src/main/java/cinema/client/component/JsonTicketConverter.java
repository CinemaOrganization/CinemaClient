package cinema.client.component;

import cinema.client.entity.Ticket;

import java.util.Collection;
import java.util.List;

public interface JsonTicketConverter {

    String objectsToJson(Collection<Ticket> objects);

    List<Ticket> toObjects(String jsonObject);
}
