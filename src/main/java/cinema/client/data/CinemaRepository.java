package cinema.client.data;

import cinema.client.entity.Cinema;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends CrudRepository<Cinema, Long> {
}
