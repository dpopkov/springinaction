package learn.sprng.action6.c04e02mongodb.data;

import learn.sprng.action6.c04e02mongodb.Ingredient;

import java.util.Optional;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
