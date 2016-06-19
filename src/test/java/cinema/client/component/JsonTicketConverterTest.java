package cinema.client.component;


import cinema.client.entity.Ticket;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JsonTicketConverterTest {

    String expectedJsonString = "[{\"number\":5,\"row\":4},{\"number\":6,\"row\":3}]";
    List<Ticket> expectedTickets;

    @Before
    public void setUp() {
        expectedTickets = new ArrayList<>();
        Ticket ticket = new Ticket();
        ticket.setRow(4);
        ticket.setNumber(5);
        expectedTickets.add(ticket);
        ticket = new Ticket();
        ticket.setRow(3);
        ticket.setNumber(6);
        expectedTickets.add(ticket);
    }

    @Test
    public void shouldConvertJsonToTickets() {

        JsonTicketConverter jsonTicketConverter = new JsonTicketConverterImpl();
        List<Ticket> tickets = jsonTicketConverter.toObjects(expectedJsonString);
        assertNotNull(tickets);
        assertEquals(expectedTickets, tickets);
    }

    @Test
    public void shouldConvertTicketsToJson() {

        JsonTicketConverter jsonTicketConverter = new JsonTicketConverterImpl();
        String json = jsonTicketConverter.objectsToJson(expectedTickets);
        assertNotNull(json);
        assertEquals(expectedJsonString, json);
    }

}
