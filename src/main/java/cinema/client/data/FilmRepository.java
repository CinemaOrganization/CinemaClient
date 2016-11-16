package cinema.client.data;


import cinema.client.entity.Film;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface FilmRepository extends CrudRepository<Film,Long> {

    @Override
    List<Film> findAll();

    Film findByNameAndYearAndStudio(String name,int year,String studio);

 //   List<Film> findByDateBetween(LocalDate startDate, LocalDate finishDate);

}
