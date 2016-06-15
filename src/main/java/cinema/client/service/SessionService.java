package cinema.client.service;

import cinema.client.data.CinemaRepository;
import cinema.client.data.FilmRepository;
import cinema.client.data.SessionRepository;
import cinema.client.entity.Film;
import cinema.client.entity.Session;
import cinema.client.web.exeptions.SessionByFilmAndDateNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessionService {

    SessionRepository sessionRepository;
    CinemaRepository cinemaRepository;
    FilmRepository filmRepository;
    static Logger logger = Logger.getLogger(SessionService.class);


    @Autowired
    public SessionService(SessionRepository sessionRepository,
                          CinemaRepository cinemaRepository,
                          FilmRepository filmRepository) {
        this.sessionRepository = sessionRepository;
        this.filmRepository = filmRepository;
        this.cinemaRepository = cinemaRepository;
    }

    public List<Session> findByFilmAndDateOrderByCinemaAndHallAndTime(long id_film, String strDate) {

        Film film = filmRepository.findOne(id_film);
        if (film == null) {
            throw new SessionByFilmAndDateNotFoundException();
        }
        LocalDate nearestDate;
        if(strDate.equals("nearest")) {
            Optional<LocalDate> date = sessionRepository.findByFilmAndWhereDateAfterOrEqual(film, LocalDate.now())
                    .stream()
                    .map(Session::getDate)
                    .findFirst();
            if (!date.isPresent()) {
                throw new SessionByFilmAndDateNotFoundException();
            }
            nearestDate = date.get();
        } else {
            try {
                nearestDate = LocalDate.parse(strDate);
            } catch (DateTimeParseException ex) {
                throw new SessionByFilmAndDateNotFoundException();
            }
        }

        List<Session> list = sessionRepository.findByFilmAndDateOrderByCinemaAndHallAndTime(film, nearestDate);
        if (list.size() == 0) {
            throw new SessionByFilmAndDateNotFoundException();
        }
        return list;
    }

    public List<LocalDate> getAllSessionsByFilmDatesAsStrings(Film film) {
        return sessionRepository.findByFilm(film).stream()
                .map(Session::getDate)
                .collect(Collectors.toList());
    }

    public List<Session> findByFilm(Film film){
        return sessionRepository.findByFilm(film);
    }

    public void save(Iterable<Session> sessions){
        sessionRepository.save(sessions);
        for (Session session : sessions){
            logger.info("Добавлен/изменён сеанс " + session);
        }
    }

    public void delete(Iterable<Session> sessions){
        sessionRepository.delete(sessions);
        for (Session session : sessions){
            logger.info("Удалён сеанс " + session);
        }

    }

    public Session findOne(long session_id) {
        return sessionRepository.findOne(session_id);
    }

    public boolean isExistedSession(Session session){
        Session existedSession = sessionRepository.findByHallAndFilmAndCinemaAndDateAndTime(
                session.getHall(),session.getFilm(),session.getCinema(),session.getDate(),session.getTime());
        if (existedSession != null){
            return true;
        }else {
            return false;
        }
    }

    public boolean isAnotherExistedSession(Session session){
        Session foundSession = sessionRepository.findByHallAndFilmAndCinemaAndDateAndTime(
                session.getHall(),session.getFilm(),session.getCinema(),session.getDate(),session.getTime());
        if (foundSession != null && foundSession.getId() != session.getId()){
            return true;
        }else {
            return false;
        }
    }
}
