package cinema.client.data;

import cinema.client.entity.Comment;
import cinema.client.entity.Film;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Query("select c from Comment c where c.film = ?1 order by c.time desc")
    List<Comment> findByFilmAndOrderByTime(Film film);
}
