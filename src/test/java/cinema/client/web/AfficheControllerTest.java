package cinema.client.web;

import cinema.client.SetupData.SessionSetupData;
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

public class AfficheControllerTest {


    @Test
    public void shouldShowRecentFilms() throws Exception {
        List<Film> expectedFilms = SessionSetupData.setupData()
                .stream()
                .map(Session::getFilm)
                .collect(Collectors.toList());
        FilmService mockService =
                mock(FilmService.class);
        when(mockService.findAll())
                .thenReturn(expectedFilms);
        AfficheController controller =
                new AfficheController(mockService);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/affiche.jsp"))
                .build();
        mockMvc.perform(get("/affiche"))
                .andExpect(view().name("affiche"))
                .andExpect(model().attributeExists("filmList"))
                .andExpect(model().attribute("filmList",
                        hasItems(expectedFilms.toArray())));
    }


}
