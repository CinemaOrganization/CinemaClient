package cinema.client.data;

import cinema.client.entity.Session;

import java.util.List;

public interface SessionRepository {
    List<Session> findByCinemaAndFilmLike(long film_id, long cinema_id);
}
