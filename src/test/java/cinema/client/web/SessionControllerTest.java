package cinema.client.web;

import cinema.client.SetupData.SessionSetupData;
import cinema.client.config.RootConfig;
import cinema.client.entity.Cinema;
import cinema.client.entity.Film;
import cinema.client.entity.Hall;
import cinema.client.entity.Session;
import cinema.client.service.CinemaService;
import cinema.client.service.FilmService;
import cinema.client.service.HallService;
import cinema.client.service.SessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class SessionControllerTest {

    @Test
    public void requireSessionByFilmAndDateAndOrderedByCinemaAndHallAndTime() throws Exception {

        List<Session> expectedSessions = SessionSetupData.setupData();
        SessionService mockSessionService = mock(SessionService.class);
        CinemaService mockCinemaService = mock(CinemaService.class);
        FilmService mockFilmService = mock(FilmService.class);
        HallService mockHallService = mock(HallService.class);

        long film_id = expectedSessions.get(0).getFilm().getId();
        String date = expectedSessions.get(0).getDate().toString();

        Set<Hall> expectedHalls = expectedSessions.stream().map(Session::getHall).collect(Collectors.toSet());
        Set<Cinema> expectedCinemas = expectedSessions.stream().map(Session::getCinema).collect(Collectors.toSet());
        Film expectedFilm = expectedSessions.stream().map(Session::getFilm).collect(Collectors.toList()).get(0);
        List<LocalDate> expectedDates = expectedSessions.stream().map(Session::getDate).collect(Collectors.toList());

        when(mockSessionService.findByFilmAndDateOrderByCinemaAndHallAndTime(film_id, date))
                .thenReturn(expectedSessions);
        when(mockCinemaService.findBySessions(expectedSessions))
                .thenReturn(expectedCinemas);
        when(mockHallService.findBySessions(expectedSessions))
                .thenReturn(expectedHalls);
        when(mockFilmService.findOne(film_id))
                .thenReturn(expectedFilm);
        when(mockSessionService.getAllSessionsByFilmDatesAsStrings(expectedFilm))
                .thenReturn(expectedDates);

        SessionController controller =
                new SessionController(mockCinemaService, mockFilmService, mockHallService, mockSessionService);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/session.jsp"))
                .build();
        mockMvc.perform(get("/session?film_id=" + film_id + "&strDate=" + date))
                .andExpect(view().name("session"))
                .andExpect(model().attributeExists("sessionList"))
                .andExpect(model().attribute("sessionList",
                        hasItems(expectedSessions.toArray())))
                .andExpect(model().attributeExists("hallList"))
                .andExpect(model().attribute("hallList",
                        hasItems(expectedHalls.toArray())))
                .andExpect(model().attributeExists("cinemaList"))
                .andExpect(model().attribute("cinemaList",
                        hasItems(expectedCinemas.toArray())))
                .andExpect(model().attributeExists("dateList"))
                .andExpect(model().attribute("dateList",
                        hasItems(expectedDates.toArray())))
                .andExpect(model().attributeExists("film"))
                .andExpect(model().attribute("film",expectedFilm));
    }
}