package cinema.client.service;

import cinema.client.entity.Ticket;

import java.util.Collection;

interface JsonTicketConverter {

    public String objectToJson(Ticket object);

    public Ticket toObject(String jsonObject) ;

    public String objectsToJson(Collection<Ticket> objects);

    public Collection<Ticket> toObjects(String jsonObject);
}
