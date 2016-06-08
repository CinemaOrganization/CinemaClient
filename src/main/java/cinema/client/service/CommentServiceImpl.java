package cinema.client.service;

import cinema.client.data.CommentRepository;
import cinema.client.data.FilmRepository;
import cinema.client.data.UserRepository;
import cinema.client.entity.Comment;
import cinema.client.entity.Film;
import cinema.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;
    FilmRepository filmRepository;
    UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository
            , FilmRepository filmRepository
            , UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Comment addNewComment(Comment comment) {
        Film film = filmRepository.findOne(comment.getFilm().getId());
        User user = userRepository.findByUsername(comment.getUser().getUsername());
        comment.setFilm(film);
        comment.setUser(user);
        comment.setTime(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByFilmAndOrderByTime(long film_id) {
        Film film = filmRepository.findOne(film_id);
        List<Comment> comments = commentRepository.findByFilmAndOrderByTime(film);
        setTimeFormatter(comments);
        return comments;
    }

    private void setTimeFormatter(List<Comment> comments) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd mm yyyy HH:mm");
        comments.forEach(comment -> comment.getTime().format(formatter));
    }

    @Transactional
    @Override
    public void removeComment(long comment_id) {
        commentRepository.delete(comment_id);
    }
}
