package cinema.client.service;

import cinema.client.entity.Ticket;

import java.util.Collection;
import java.util.List;

interface JsonTicketConverter {

    public String objectToJson(Ticket object);

    public Ticket toObject(String jsonObject) ;

    public String objectsToJson(Collection<Ticket> objects);

    public List<Ticket> toObjects(String jsonObject);
}
