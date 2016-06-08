package cinema.client.service;

import cinema.client.data.FilmRepository;
import cinema.client.data.SessionRepository;
import cinema.client.entity.Film;
import cinema.client.entity.Session;
import org.apache.log4j.Logger;
import org.mockito.internal.util.collections.Iterables;
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
    static Logger logger = Logger.getLogger(FilmService.class);

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

    public boolean isExistedFilm(Film film){
        Film existFilm =filmRepository.findByNameAndYearAndStudio(film.getName(),film.getYear(),film.getStudio());
        if (existFilm != null){
            return true;
        }else {
            return false;
        }
    }

    public boolean isAnotherExistedFilm(Film film){
        Film foundFilm = filmRepository.findByNameAndYearAndStudio(film.getName(),film.getYear(),film.getStudio());
        if (foundFilm != null && foundFilm.getId() != film.getId()){
            return true;
        }else {
            return false;
        }
    }
    public void saveFilms(Iterable<Film> films) {
        filmRepository.save(films);
        for (Film film : films){
            logger.info("Добавлен/изменён фильм " + film);
        }
    }
    public void deleteFilm(long id){
        filmRepository.delete(id);
        logger.info("Удалён филь с ИД = " + id);
    }

    public List<Film> findByStartDateAfter(LocalDate date) {
        List<Session> sessions = sessionRepository.findByDateGreaterThanEqual(date);
        Set<Film> films = sessions.stream().map(Session::getFilm).collect(Collectors.toSet());
        return new ArrayList<>(films);
    }
}
