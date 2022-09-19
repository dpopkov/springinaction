package learn.sprng.action6.c06e01properties.data;

import learn.sprng.action6.c06e01properties.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
