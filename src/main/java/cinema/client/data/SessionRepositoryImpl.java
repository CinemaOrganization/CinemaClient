package cinema.client.data;

import cinema.client.entity.Cinema;
import cinema.client.entity.Film;
import cinema.client.entity.Hall;
import cinema.client.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessionRepositoryImpl implements SessionRepository {

    List<Session> savedSessions;
    String cinemaTempName = "First Cinema";

    @Autowired
    public SessionRepositoryImpl(FilmRepository filmRepository) {
        List<Film> filmList = filmRepository.findAll();
        savedSessions = new ArrayList<>();

        filmList.forEach(film -> {
            Cinema cinema = new Cinema(cinemaTempName);
            Hall hall = new Hall();
            hall.setCinema(cinema);
            for (int i = 0; i < 3; i++) {
                Session session = new Session();
                session.setCost(100 * i / 2);
                session.setTime(LocalDateTime.of(2020, 1, 12, 12 + i, i * 10));
                session.setFilm(film);
                session.setHall(hall);
                savedSessions.add(session);
            }
        });
    }

    @Override
    public List<Session> findByCinemaAndFilmLike(long film_id, long cinema_id) {
        return savedSessions
                .stream()
                .filter(session -> session.getFilm().getId() == film_id)
                .filter(session1 -> session1.getHall().getCinema().getId() == cinema_id)
                .collect(Collectors.toList());
    }
}
