package cinema.client.data;

import cinema.client.entity.Film;

import java.util.List;

public interface FilmRepository {
    List<Film> findByNameLike(String name);
    List<Film> findFilms(int number);
}
