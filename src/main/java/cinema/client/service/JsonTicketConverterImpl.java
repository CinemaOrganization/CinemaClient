package cinema.client.service;

import cinema.client.entity.Ticket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

@Service
public class JsonTicketConverterImpl implements JsonTicketConverter {

    public String objectToJson(Ticket object) {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Ticket toObject(String jsonObject) {
        ObjectMapper mapper = new ObjectMapper();
        Ticket object = null;
        try {
            object = mapper.readValue(jsonObject, Ticket.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    public String objectsToJson(Collection<Ticket> objects) {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Collection<Ticket> toObjects(String jsonObject) {
        ObjectMapper mapper = new ObjectMapper();
        Collection objects = null;
        try {
            objects = mapper.readValue(jsonObject, Collection.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }
}
