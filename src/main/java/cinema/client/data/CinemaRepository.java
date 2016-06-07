package cinema.client.data;

import cinema.client.entity.Cinema;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends CrudRepository<Cinema, Long> {
    @Override
    List<Cinema> findAll();

    Cinema findByName(String name);
}
