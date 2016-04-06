package cinema.client.service;

import cinema.client.SetupData.Datas;
import cinema.client.data.CinemaRepository;
import cinema.client.data.FilmRepository;
import cinema.client.data.SessionRepository;
import cinema.client.entity.Film;
import cinema.client.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessionService {

    SessionRepository sessionRepository;
    CinemaRepository cinemaRepository;
    FilmRepository filmRepository;


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
        LocalDate nearestDate;
        if(strDate.equals("nearest")) {
            nearestDate = sessionRepository.findByFilmAndWhereDateAfterOrEqual(film, LocalDate.now())
                    .stream()
                    .map(Session::getDate)
                    .collect(Collectors.toSet())
                    .iterator()
                    .next();
        } else {
            nearestDate = LocalDate.parse(strDate);
        }

        List<Session> list = sessionRepository.findByFilmAndDateOrderByCinemaAndHallAndTime(film, nearestDate);
        return list;
    }

    public List<LocalDate> getAllSessionsByFilmDatesAsStrings(Film film) {
        return sessionRepository.findByFilm(film).stream()
                .map(Session::getDate)
                .collect(Collectors.toList());
    }

    //Временно, для добавления временных данных в базу
    public void add() {
        try {
            Datas datas = new Datas(sessionRepository);
            datas.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Временно, для удаления временных данных из базы
    public void deteteData() {
        try {
            Datas datas = new Datas(sessionRepository);
            datas.tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
