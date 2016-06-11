package cinema.client.data;

import cinema.client.SetupData.SetupData;
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
    private HallRepository hallRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private SessionRepository sessionRepository;

    SetupData setupData = new SetupData();
    private List<Session> sessions;

    @Before
    @Rollback(false)
    public void setUp() {

        sessions = setupData.getSessions();
        cinemaRepository.save(sessions.stream().map(Session::getCinema).collect(Collectors.toList()));
        hallRepository.save(sessions.stream().map(Session::getHall).collect(Collectors.toList()));
        filmRepository.save(sessions.stream().map(Session::getFilm).collect(Collectors.toList()));
        sessionRepository.save(sessions);
    }

    @Test
    public void sessionRepository_ShouldFindByFilmAndDateOrderByCinemaAndHallAndTime() {

        Film chosenFilm = sessions.get(0).getFilm();
        LocalDate chosenDate = sessions.get(0).getDate();
        Comparator<Session> cinemaCompare = Comparator.comparing(Session::getCinema,
                (o1, o2) -> Long.compare(o1.getId(), o2.getId()));
        Comparator<Session> hallCompare = Comparator.comparing(Session::getHall,
                (o1, o2) -> Long.compare(o1.getId(), o2.getId()));
        Comparator<Session> timeCompare = Comparator.comparing(Session::getTime);

        List<Session> expectedSessions = sessions.stream()
                .filter(session -> session.getFilm().equals(chosenFilm))
                .filter(session -> session.getDate().equals(chosenDate))
                .sorted(cinemaCompare.thenComparing(hallCompare).thenComparing(timeCompare))
                .collect(Collectors.toList());

        List<Session> sessions = sessionRepository.findByFilmAndDateOrderByCinemaAndHallAndTime(chosenFilm, chosenDate);

        assertNotNull(sessions);
        assertEquals(expectedSessions, sessions);
    }

    @Test
    public void sessionRepository_ShouldFindByFilmAndWhereDateAfterOrEqual() {
        Film chosenFilm = sessions.get(0).getFilm();
        LocalDate chosenDate = sessions.get(0).getDate();
        List<Session> expectedSessions = sessions.stream()
                .filter(session -> session.getFilm().equals(chosenFilm))
                .filter(session -> session.getDate().isEqual(chosenDate) || session.getDate().isAfter(chosenDate))
                .collect(Collectors.toList());
        List<Session> sessions = sessionRepository.findByFilmAndWhereDateAfterOrEqual(chosenFilm, chosenDate);

        assertNotNull(sessions);
        assertEquals(expectedSessions, sessions);
    }

    @Test
    public void sessionRepository_ShouldFindByFilm() {
        Film chosenFilm = sessions.get(0).getFilm();
        List<Session> expectedSessions = sessions.stream()
                .filter(session -> session.getFilm().equals(chosenFilm))
                .collect(Collectors.toList());
        List<Session> sessions = sessionRepository.findByFilm(chosenFilm);

        assertNotNull(sessions);
        assertEquals(expectedSessions, sessions);
    }

    @After
    public void tearDown() {
        sessionRepository.deleteAll();
    }
}
