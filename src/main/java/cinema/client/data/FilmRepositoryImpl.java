package cinema.client.data;

import cinema.client.entity.Film;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmRepositoryImpl implements FilmRepository {

    List<Film> savedFilms;
    static final int NUMBER_SAVED_FILMS = 23;
//            = new ArrayList<Film>(){{
//        add(new Film("Золушка", LocalTime.of(1,30),"Каламбия"));
//        add(new Film("Красавица и чудовище", LocalTime.of(2,10),"Каламбия"));
//        add(new Film("Пакахонтас", LocalTime.of(1,5),"Каламбия"));
//    }};

    public FilmRepositoryImpl() {
        savedFilms = new ArrayList<Film>();
        for (int i = 0; i < NUMBER_SAVED_FILMS; i++) {
            savedFilms.add(new Film("film"+i, LocalTime.of(i,i+20),"description"+i));
        }
    }

    @Override
    public List<Film> findByNameLike(String name) {
        return savedFilms
                .stream()
                .filter(name::equals)
                .collect(Collectors.toList());
    }

    @Override
    public List<Film> findFilms(int number) {
        if (number < savedFilms.size()) {
            return savedFilms.subList(0, number);
        }
        return savedFilms;
    }

}
