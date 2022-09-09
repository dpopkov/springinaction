package learn.sprng.action6.c03e02datajdbc.data;

import learn.sprng.action6.c03e02datajdbc.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
