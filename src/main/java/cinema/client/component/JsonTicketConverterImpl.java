package cinema.client.component;

import cinema.client.entity.Ticket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JsonTicketConverterImpl implements JsonTicketConverter {

    public String objectsToJson(Collection<Ticket> tickets) {
        List<Place> places = removeExtraInfo(tickets);
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(places);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Ticket> toObjects(String jsonObject) {
        ObjectMapper mapper = new ObjectMapper();
        List<Ticket> objects = null;
        try {
            objects = Arrays.asList(mapper.readValue(jsonObject, Ticket[].class));
        } catch (IOException e) {
            e.printStackTrace();//неверный формат или пустая строка брони
        }
        return objects;
    }

    private List<Place> removeExtraInfo(Collection<Ticket> objects) {
        return objects.stream().map(ticket -> {
            Place place = new Place();
            place.setNumber(ticket.getNumber());
            place.setRow(ticket.getRow());
            return place;
        }).collect(Collectors.toList());
    }

    private class Place {
        private int number;

        private int row;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Place place = (Place) o;

            if (number != place.number) return false;
            return row == place.row;

        }

        @Override
        public int hashCode() {
            int result = number;
            result = 31 * result + row;
            return result;
        }
    }
}
