package cinema.client.web;

import cinema.client.SetupData.SetupData;
import cinema.client.entity.Film;
import cinema.client.entity.Session;
import cinema.client.service.FilmService;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class HomeControllerTest {

    SetupData setupData = new SetupData();

    @Test
    public void homeController_shouldShowRecentFilms() throws Exception {
        List<Film> expectedFilms = setupData.getSessions()
                .stream()
                .map(Session::getFilm)
                .collect(Collectors.toList());
        FilmService mockService =
                mock(FilmService.class);
        when(mockService.findByDates("now","empty"))
                .thenReturn(expectedFilms);
        HomeController controller =
                new HomeController(mockService);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/home.jsp"))
                .build();
        mockMvc.perform(get("/"))
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("filmList"))
                .andExpect(model().attribute("filmList",
                        hasItems(expectedFilms.toArray())));
    }


}
