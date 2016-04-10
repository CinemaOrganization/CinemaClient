package cinema.client.SetupData;


import cinema.client.data.HallRepository;
import cinema.client.data.PlacesRepository;
import cinema.client.entity.Cinema;
import cinema.client.entity.Hall;
import cinema.client.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SetupPlaces {


    static HallRepository hallRepository;
    static PlacesRepository placesRepository;

    @Autowired
    public SetupPlaces(HallRepository hallRepository, PlacesRepository placesRepository) {
        this.hallRepository = hallRepository;
        this.placesRepository = placesRepository;
    }

    public static void init() {

        TreeSet<Place> placeSet = new TreeSet<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 11; j++) {
                Place place = new Place();
                place.setNumber(i*10 + j);
                place.setRow(i + 1);
                placeSet.add(place);
            }
        }
        placesRepository.save(placeSet);
        List<Hall> hallList = new ArrayList<Hall>();
        Cinema cinema = new Cinema("Победа");
        Hall hall = new Hall(cinema,9,40,false);
        hall.setPlaceSet(placeSet);
        hallList.add(hall);
        hallRepository.save(hallList);
    }
}
