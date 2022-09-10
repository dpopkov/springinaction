package learn.sprng.action6.c03e03datajpa.data;

import learn.sprng.action6.c03e03datajpa.Ingredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class IngredientRepositoryIT {
    @Autowired
    IngredientRepository ingredientRepository;

    @Test
    void findById() {
        Ingredient flto = ingredientRepository.findById("FLTO").orElseThrow();
        assertEquals("Flour Tortilla", flto.getName());
        assertEquals(Ingredient.Type.WRAP, flto.getType());

        Optional<Ingredient> none = ingredientRepository.findById("NONE");
        assertThat(none.isEmpty()).isTrue();
    }
}
