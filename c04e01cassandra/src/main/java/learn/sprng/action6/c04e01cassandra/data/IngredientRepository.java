package learn.sprng.action6.c04e01cassandra.data;

import learn.sprng.action6.c04e01cassandra.Ingredient;

import java.util.Optional;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
