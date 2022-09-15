package learn.sprng.action6.c05e01security.data;

import learn.sprng.action6.c05e01security.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
