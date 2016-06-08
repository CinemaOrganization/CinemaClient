package cinema.client.web;

import cinema.client.SetupData.SetupData;
import cinema.client.config.RootConfig;
import cinema.client.entity.*;
import cinema.client.service.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
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

    List<Session> expectedSessions;
    SetupData setupData = new SetupData();


    @Before
    public void setUp() throws Exception {
        expectedSessions = setupData.getSessions();
    }

    @Test
    public void sessionController_requireSessionByFilmAndDateAndOrderedByCinemaAndHallAndTime() throws Exception {

        Set<Hall> expectedHalls = expectedSessions.stream().map(Session::getHall).collect(Collectors.toSet());
        Set<Cinema> expectedCinemas = expectedSessions.stream().map(Session::getCinema).collect(Collectors.toSet());
        Film expectedFilm = expectedSessions.stream().map(Session::getFilm).collect(Collectors.toList()).get(0);
        List<LocalDate> expectedDates = expectedSessions.stream().map(Session::getDate).collect(Collectors.toList());
        List<Comment> expectedComments = new ArrayList<>();
        expectedComments.add(new Comment());


        SessionService mockSessionService = mock(SessionService.class);
        CinemaService mockCinemaService = mock(CinemaService.class);
        FilmService mockFilmService = mock(FilmService.class);
        HallService mockHallService = mock(HallService.class);
        CommentService mockCommentService = mock(CommentService.class);

        long film_id = expectedSessions.get(0).getFilm().getId();
        String date = expectedSessions.get(0).getDate().toString();


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
        when(mockCommentService.findByFilmAndOrderByTime(expectedFilm.getId()))
                .thenReturn(expectedComments);

        SessionController controller =
                new SessionController(mockCinemaService
                        , mockFilmService
                        , mockHallService
                        , mockSessionService
                        , mockCommentService);
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
                .andExpect(model().attributeExists("commentList"))
                .andExpect(model().attribute("commentList",
                        hasItems(expectedComments.toArray())))
                .andExpect(model().attributeExists("film"))
                .andExpect(model().attribute("film", expectedFilm));
    }
}