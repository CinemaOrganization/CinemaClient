package cinema.client.service;

import cinema.client.data.FilmRepository;
import cinema.client.data.SessionRepository;
import cinema.client.entity.Film;
import cinema.client.entity.Session;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class FilmService {

    FilmRepository filmRepository;
    SessionRepository sessionRepository;
    ImageService imageService;
    static Logger logger = Logger.getLogger(FilmService.class);

    @Autowired
    public FilmService(FilmRepository filmRepository, SessionRepository sessionRepository, ImageService imageService) {
        this.filmRepository = filmRepository;
        this.sessionRepository = sessionRepository;
        this.imageService = imageService;
    }


    public List<Film> findAll() {
        return filmRepository.findAll();
    }


    public Film findOne(long film_id) {
        return filmRepository.findOne(film_id);
    }

    public boolean isExistedFilm(Film film) {
        Film existFilm = filmRepository.findByNameAndYearAndStudio(film.getName(), film.getYear(), film.getStudio());
        if (existFilm != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAnotherExistedFilm(Film film) {
        Film foundFilm = filmRepository.findByNameAndYearAndStudio(film.getName(), film.getYear(), film.getStudio());
        if (foundFilm != null && foundFilm.getId() != film.getId()) {
            return true;
        } else {
            return false;
        }
    }


    public void saveFilms(Iterable<Film> films) {
        saveImagesForFilms(films);
        filmRepository.save(films);
        for (Film film : films) {
            logger.info("Добавлен/изменён фильм " + film);
        }
    }

    private void saveImagesForFilms(Iterable<Film> films) {
        for (Film currentFilm : films) {
 //           String imageId = imageService.saveImage(currentFilm.getImage());
            currentFilm.setImageId("-1");
        }
    }

    public void deleteFilm(long id) {
        filmRepository.delete(id);
        logger.info("Удалён фильм с ИД = " + id);
    }

    public List<Film> findByStartDateAfter(LocalDate date) {
        List<Session> sessions = sessionRepository.findByDateGreaterThanEqual(date);
        Set<Film> films = sessions.stream().map(Session::getFilm).collect(Collectors.toSet());
        return new ArrayList<>(films);
    }

    private List<Film> findByStartDateBetween(String date1, String date2) {
        LocalDate startDate = LocalDate.parse(date1);
        LocalDate finishDate = LocalDate.parse(date2);
        if (startDate.isAfter(finishDate)) {
            LocalDate temp = finishDate;
            finishDate = startDate;
            startDate = temp;
        }
        List<Session> sessions = sessionRepository.findByDateBetween(startDate, finishDate);
        Set<Film> films = sessions.stream().map(Session::getFilm).collect(Collectors.toSet());
        return new ArrayList<>(films);
    }

    public List<Film> findByDates(String date1, String date2) {
        List<Film> films = new ArrayList<>();
        if (date1.equals("now")) {
            films = findByStartDateAfter(LocalDate.now());
        } else {
            if (date2.equals("empty")) {
                films = findByStartDateBetween(date1, date1);
            } else {
                films = findByStartDateBetween(date1, date2);
            }
        }
        return films;
    }
}
