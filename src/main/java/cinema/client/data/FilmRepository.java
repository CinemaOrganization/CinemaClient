package cinema.client.data;


import cinema.client.entity.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends CrudRepository<Film,Long> {

    @Override
    List<Film> findAll();
}
