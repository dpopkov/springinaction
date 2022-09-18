package learn.sprng.action6.c05e01security.web;

import learn.sprng.action6.c05e01security.AppUser;
import learn.sprng.action6.c05e01security.DefaultIngredients;
import learn.sprng.action6.c05e01security.Ingredient;
import learn.sprng.action6.c05e01security.data.AppUserRepository;
import learn.sprng.action6.c05e01security.data.IngredientRepository;
import learn.sprng.action6.c05e01security.security.SecurityConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {DesignShaurmaController.class})
class DesignShaurmaControllerTest {

    private static final String TEST_USERNAME = "testuser";
    private static final String TEST_PASSWORD = "testpass";

    @Autowired
    private MockMvc mockMvc;

    private List<Ingredient> ingredients;

    @MockBean
    private IngredientRepository ingredientRepository;

    @MockBean
    private AppUserRepository appUserRepository;

    /* The mock beans below are here to avoid autowiring issues. */
    @MockBean
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        ingredients = DefaultIngredients.getList();
        when(ingredientRepository.findAll()).thenReturn(ingredients);

        DefaultIngredients.getIds().forEach(this::initIngredientRepoMock);

        when(appUserRepository.findByUsername(TEST_USERNAME))
                .thenReturn(Optional.of(new AppUser(1L, TEST_USERNAME, TEST_PASSWORD,
                        SecurityConstants.ROLE_USER, "123 Street", "Someville",
                        "CO", "12345")));
    }

    private void initIngredientRepoMock(String id) {
        when(ingredientRepository.findById(id)).thenReturn(Optional.of(DefaultIngredients.getById(id)));
    }

    @Test
    @WithMockUser(username = TEST_USERNAME, password = TEST_PASSWORD)
    void showDesignForm() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpect(model().attribute("wrap", ingredients.subList(0, 2)))
                .andExpect(model().attribute("protein", ingredients.subList(2, 4)))
                .andExpect(model().attribute("veggies", ingredients.subList(4, 6)))
                .andExpect(model().attribute("cheese", ingredients.subList(6, 8)))
                .andExpect(model().attribute("sauce", ingredients.subList(8, 10)));
    }

    @Test
    @WithMockUser(username = TEST_USERNAME, password = TEST_PASSWORD)
    public void processDesignForm() throws Exception {
        mockMvc.perform(post("/design").with(csrf())
                .content("name=Test+Shaurma&ingredients=FLTO,GRBF,CHED")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().stringValues("Location", "/orders/current"));
    }
}
