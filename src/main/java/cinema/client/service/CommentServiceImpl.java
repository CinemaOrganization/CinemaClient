package cinema.client.service;

import cinema.client.data.CommentRepository;
import cinema.client.data.FilmRepository;
import cinema.client.data.UserRepository;
import cinema.client.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void addNewComment(Comment comment) {

    }
}
