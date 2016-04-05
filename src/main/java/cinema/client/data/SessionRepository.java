package cinema.client.data;

import cinema.client.entity.Cinema;
import cinema.client.entity.Film;
import cinema.client.entity.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
    @Query("select s from  Session s where s.film = ?1 and s.date = ?2 order by s.cinema,s.hall,s.time")
    List<Session> findByFilmAndDateOrderByCinemaAndHallAndTime(Film film, LocalDate date);
}
