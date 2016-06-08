package cinema.client.service;

import cinema.client.data.FilmRepository;
import cinema.client.data.SessionRepository;
import cinema.client.entity.Film;
import cinema.client.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilmService {

    FilmRepository filmRepository;
    SessionRepository sessionRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository, SessionRepository sessionRepository) {
        this.filmRepository = filmRepository;
        this.sessionRepository = sessionRepository;
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }


    public Film findOne(long film_id) {
        return filmRepository.findOne(film_id);
    }

    public void saveFilms(Iterable<Film> films)
    {
        filmRepository.save(films);
    }
    public void deleteFilm(long id){
        filmRepository.delete(id);
    }

    public List<Film> findByStartDateAfter(LocalDate date) {
        List<Session> sessions = sessionRepository.findByDateGreaterThanEqual(date);
        Set<Film> films = sessions.stream().map(Session::getFilm).collect(Collectors.toSet());
        return new ArrayList<>(films);
    }
}
