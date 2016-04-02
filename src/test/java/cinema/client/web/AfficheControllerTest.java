package cinema.client.web;

import cinema.client.data.FilmRepository;
import cinema.client.entity.Film;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AfficheControllerTest {
    @Test
    public void shouldShowRecentSpittles() throws Exception {
        final int count = 20;

        List<Film> expectedFilms = createFilmsList(count);
        FilmRepository mockRepository =
                mock(FilmRepository.class);
        when(mockRepository.findFilms(count))
                .thenReturn(expectedFilms);
        AfficheController controller =
                new AfficheController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/views/affiche.jsp"))
                .build();
        mockMvc.perform(get("/affiche"))
                .andExpect(view().name("affiche"))
                .andExpect(model().attributeExists("filmList"))
                .andExpect(model().attribute("filmList",
                        hasItems(expectedFilms.toArray())));
    }

    private List<Film> createFilmsList(int count) {
        List<Film> films = new ArrayList<Film>();
        for (int i = 0; i < count; i++) {
            films.add(new Film("film" + i, LocalTime.of(i, i + 20), "description" + i));
        }
        return films;
    }

}
