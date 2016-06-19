package cinema.client.service;

import cinema.client.SetupData.SetupData;
import cinema.client.data.CinemaRepository;
import cinema.client.data.FilmRepository;
import cinema.client.data.SessionRepository;
import cinema.client.entity.Film;
import cinema.client.entity.Session;
import cinema.client.web.exeptions.SessionByFilmAndDateNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SessionServiceTest {

    SessionRepository sessionRepository;
    CinemaRepository cinemaRepository;
    FilmRepository filmRepository;
    SetupData setupData = new SetupData();

    @Before
    public void setUp() throws Exception {
        sessionRepository = mock(SessionRepository.class);
        cinemaRepository = mock(CinemaRepository.class);
        filmRepository = mock(FilmRepository.class);
    }

    @Test(expected = SessionByFilmAndDateNotFoundException.class)
    public void shouldSearchFilmAndThrowException() {
        long film_id = 0;
        String strDate = "nearest";
        when(filmRepository.findOne(film_id)).thenReturn(null);
        SessionService sessionService = new SessionService(sessionRepository, cinemaRepository, filmRepository);
        sessionService.findByFilmAndDateOrderByCinemaAndHallAndTime(film_id,strDate);
    }

    @Test
    public void shouldSearchSessionByFilmAndNearestDate() {
        Film expectedFilm = setupData.getFilms().get(0);
        String strDate = "nearest";
        Session expectedSession = setupData.getSessions().get(0);

        when(filmRepository
                .findOne(expectedFilm.getId())).thenReturn(expectedFilm);
        when(sessionRepository
                .findByFilmAndWhereDateAfterOrEqual(expectedFilm,expectedSession.getDate()))
                .thenReturn(new ArrayList<Session>(){{add(expectedSession);}});
        when(sessionRepository.findByFilmAndDateOrderByCinemaAndHallAndTime(expectedFilm,expectedSession.getDate()))
                .thenReturn(new ArrayList<Session>(){{add(expectedSession);}});

        SessionService sessionService = new SessionService(sessionRepository, cinemaRepository, filmRepository);
        List<Session> sessions = sessionService
                .findByFilmAndDateOrderByCinemaAndHallAndTime(expectedFilm.getId(),"nearest");
        assertNotNull(sessions);
        assertEquals(sessions.get(0),expectedSession);
    }

}
