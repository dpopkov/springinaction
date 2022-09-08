package learn.sprng.action6.c03e01jdbc.data;

import learn.sprng.action6.c03e01jdbc.Ingredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IngredientRepositoryJdbcIT {

    @Autowired
    private IngredientRepositoryJdbc repository;

    @Test
    void findById() {
        Ingredient ingredient = repository.findById("FLTO").orElseThrow();
        assertThat(ingredient).isEqualTo(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));

        Optional<Ingredient> absent = repository.findById("NONE");
        assertThat(absent.isEmpty()).isTrue();
    }
}
