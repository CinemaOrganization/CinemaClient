package cinema.client.service;

import cinema.client.SetupData.SetupData;
import cinema.client.data.CommentRepository;
import cinema.client.data.FilmRepository;
import cinema.client.data.UserRepository;
import cinema.client.entity.Comment;
import cinema.client.entity.Film;
import cinema.client.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



public class CommentServiceTest {

    CommentRepository commentRepository;
    FilmRepository filmRepository;
    UserRepository userRepository;
    SetupData setupData = new SetupData();

    @Before
    public void setUp() throws Exception {

        commentRepository = mock(CommentRepository.class);
        filmRepository = mock(FilmRepository.class);
        userRepository = mock(UserRepository.class);

    }

    @Test
    public void commentService_ShouldAddNewComment() {
        Comment expectedComment = setupData.getComments().get(0);
        Film expectedFilm = setupData.getFilms().get(0);
        User expectedUser = setupData.getUsers().get(0);

        when(filmRepository.findOne(expectedFilm.getId()))
                .thenReturn(expectedFilm);
        when(userRepository.findByUsername(expectedUser.getUsername()))
                .thenReturn(expectedUser);
        when(commentRepository.save(expectedComment))
                .thenReturn(expectedComment);

        CommentService commentService = new CommentServiceImpl(commentRepository,
                filmRepository, userRepository);

        Comment comment = commentService.addNewComment(expectedComment);

        assertNotNull(comment);
        assertEquals(expectedComment, comment);
    }

    @Test
    public void commentService_ShouldFindByFilmAndOrderByTime() {
        Film film = setupData.getFilms().get(0);
        List<Comment> expectedComments = setupData.getComments()
                .stream()
                .filter(comment -> comment.getFilm().equals(film))
                .collect(Collectors.toList());

        when(filmRepository.findOne(film.getId()))
                .thenReturn(film);
        when(commentRepository.findByFilmAndOrderByTime(film))
                .thenReturn(expectedComments);

        CommentService commentService = new CommentServiceImpl(commentRepository,
                filmRepository, userRepository);

        List<Comment> comments = commentService.findByFilmAndOrderByTime(film.getId());

        assertNotNull(comments);
        assertEquals(expectedComments, comments);
    }
}
