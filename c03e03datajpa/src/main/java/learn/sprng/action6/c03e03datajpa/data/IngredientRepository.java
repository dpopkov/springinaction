package learn.sprng.action6.c03e03datajpa.data;

import learn.sprng.action6.c03e03datajpa.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
