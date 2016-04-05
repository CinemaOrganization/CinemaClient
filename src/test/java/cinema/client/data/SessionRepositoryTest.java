package cinema.client.data;

import cinema.client.SetupData.SessionSetupData;
import cinema.client.config.RootConfig;
import cinema.client.entity.Film;
import cinema.client.entity.Session;
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

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Transactional
public class SessionRepositoryTest {

    @Autowired
    private SessionRepository repository;

    private List<Session> sessions;

    @Before
    @Rollback(false)
    public void setUp() throws Exception {

        repository.save(sessions = SessionSetupData.setupData());
    }

    @Test
    public void findByFilmAndDateOrderByCinemaAndHallAndTime() throws Exception {

        Film chosenFilm = sessions.get(0).getFilm();
        LocalDate chosenDate = sessions.get(0).getDate();
        List<Session> expectedSessions = sessions.stream()
                .filter(session -> session.getFilm().equals(chosenFilm))
                .filter(session -> session.getDate().equals(chosenDate))
                .sorted(Comparator.comparing(Session::getCinema, (o1, o2) -> new Long(o1.getId() - o2.getId()).intValue())
                        .thenComparing(Session::getHall, (o1, o2) -> new Long(o1.getId() - o2.getId()).intValue())
                        .thenComparing(Session::getTime)).collect(Collectors.toList());

        List<Session> sessions = repository.findByFilmAndDateOrderByCinemaAndHallAndTime(chosenFilm, chosenDate);

        assertNotNull(sessions);
        assertEquals(expectedSessions,sessions);
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }
}
