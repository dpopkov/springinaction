package learn.sprng.action6.c03e01jdbc.web;

import learn.sprng.action6.c03e01jdbc.data.IngredientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {DesignShaurmaController.class})
class DesignShaurmaControllerTest {

    @MockBean
    private IngredientRepository ingredientRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void showDesignForm() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(status().isOk())
        .andExpect(view().name("design"));
    }

    @Test
    void processDesignForm() throws Exception {
        mockMvc.perform(post("/design")
                .content("name=Test+Shaurma&ingredients=FLTO,GRBF,CHED")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(header().stringValues("Location", "/orders/current"));

    }
}
