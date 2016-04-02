package cinema.client.web;

import cinema.client.config.RootConfig;
import cinema.client.data.FilmRepository;
import cinema.client.data.SessionRepository;
import cinema.client.entity.Cinema;
import cinema.client.entity.Film;
import cinema.client.entity.Hall;
import cinema.client.entity.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class SessionControllerTest {

    @Autowired
    FilmRepository filmRepository;
    String cinemaTestName = "First Cinema";

    @Test
    public void shouldShowSessionsByFilmAndCinema() throws Exception {
        Film film = filmRepository.findAll().get(0);
        List<Session> expectedSessions = createSessionList(film);
        SessionRepository mockRepository =
                mock(SessionRepository.class);
        when(mockRepository.findByCinemaAndFilmLike(film.getId(),0))//Временно 1 кинотеарт и у него id == 0
                .thenReturn(expectedSessions);
        SessionController controller =
                new SessionController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/session.jsp"))
                .build();
        mockMvc.perform(get("/session?film_id="+film.getId()))
                .andExpect(view().name("session"))
                .andExpect(model().attributeExists("sessionList"))
                .andExpect(model().attribute("sessionList",
                        hasItems(expectedSessions.toArray())));
    }

    private List<Session> createSessionList(Film film) {
        List<Session> sessionList = new ArrayList<>();
        Cinema cinema = new Cinema(cinemaTestName);
        Hall hall = new Hall();
        hall.setCinema(cinema);
        for (int i = 0; i < 3; i++) {
            Session session = new Session();
            session.setCost(100*i/2);
            session.setTime(LocalDateTime.of(2020,1,12,12+i, i * 10));
            session.setFilm(film);
            session.setHall(hall);
            sessionList.add(session);
        }
        return sessionList;
    }
}
