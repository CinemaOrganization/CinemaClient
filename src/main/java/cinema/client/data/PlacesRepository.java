package cinema.client.data;

import cinema.client.entity.Place;
import org.springframework.data.repository.CrudRepository;

public interface PlacesRepository extends CrudRepository<Place,Long> {

}
