package learn.sprng.action6.c04e01cassandra.data;

import learn.sprng.action6.c04e01cassandra.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
