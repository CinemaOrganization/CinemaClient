package cinema.client.service;

import cinema.client.entity.Comment;

import java.util.List;

public interface CommentService {

    void addNewComment(Comment comment);

    List<Comment> findByFilmAndOrderByTime(long film);

    void removeComment(long id);
}
