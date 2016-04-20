package cinema.client.data;

import cinema.client.entity.Cinema;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends CrudRepository<Cinema, Long> {
    @Override
    List<Cinema> findAll();

    @Query("select c from  Cinema c where c.name = ?1")
    Cinema findByName(String name);
}
