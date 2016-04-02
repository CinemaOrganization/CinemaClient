package cinema.client.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class HomeControllerTest {

    HomeController controller;
    MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        controller = new HomeController();
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("home"));
    }

    //    @Test
//    public void testAffichePage() throws Exception {
//        mockMvc.perform(get("/affiche"))
//                .andExpect(view().name("affiche"));
//    }


}
