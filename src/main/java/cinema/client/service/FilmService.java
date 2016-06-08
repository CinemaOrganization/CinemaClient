package cinema.client.service;

import cinema.client.data.FilmRepository;
import cinema.client.entity.Film;
import org.apache.log4j.Logger;
import org.mockito.internal.util.collections.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    FilmRepository filmRepository;
    static Logger logger = Logger.getLogger(FilmService.class);

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
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
}
