package cinema.client.data;

import cinema.client.config.RootConfig;
import cinema.client.entity.Comment;
import cinema.client.entity.Film;
import cinema.client.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Transactional
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository repository;

    List<Comment> comments = new ArrayList<>();

    @Before
    @Rollback(false)
    public void setUp() {

        List<Film> films = new ArrayList<>();
        films.add(new Film("Бетмен", "DC", LocalTime.of(2, 20), "Фильм про героя...", 2010));
        films.add(new Film("Cупермен", "DC", LocalTime.of(3, 00), "Фильм про супергероя...", 2011));

        User user = new User("1", "2", "3", "4", "5");

        for (int i = 0; i < 10; i++) {
            comments.add(new Comment("text", LocalDateTime.now().plusMinutes(i)
                    , films.get(i % 2), user));
        }

        repository.save(comments);
    }


    @Test
    public void shouldFindByFilmAndOrderByTime() {
        Film chosenFilm = comments.get(0).getFilm();
        Comparator<Comment> timeComparator = Comparator.comparing(Comment::getTime, LocalDateTime::compareTo);
        List<Comment> expectedComments = comments.stream()
                .filter(comment -> comment.getFilm().equals(chosenFilm))
                .sorted(timeComparator.reversed())
                .collect(Collectors.toList());

        List<Comment> comments = repository.findByFilmAndOrderByTime(chosenFilm);

        assertNotNull(comments);
        assertEquals(expectedComments, comments);
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

}
