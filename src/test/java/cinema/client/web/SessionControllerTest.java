package cinema.client.web;

import cinema.client.SetupData.SessionSetupData;
import cinema.client.config.RootConfig;
import cinema.client.entity.Session;
import cinema.client.service.SessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.List;

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
    public void shouldShowSessionsByFilmAndCinema() throws Exception {

        List<Session> expectedSessions = SessionSetupData.setupData();
        SessionService mockService =
                mock(SessionService.class);
        long film_id = expectedSessions.get(0).getFilm().getId();
        String date = "today";

        when(mockService.findByFilmAndDateOrderByCinemaAndHallAndTime(film_id,date))
                .thenReturn(expectedSessions);
        SessionController controller =
                new SessionController(mockService);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/session.jsp"))
                .build();
        mockMvc.perform(get("/session?film_id="+film_id))
                .andExpect(view().name("session"))
                .andExpect(model().attributeExists("sessionList"))
                .andExpect(model().attribute("sessionList",
                        hasItems(expectedSessions.toArray())));
    }
}
