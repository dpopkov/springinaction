package learn.sprng.action6.c04e02mongodb.data;

import learn.sprng.action6.c04e02mongodb.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
