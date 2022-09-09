package learn.sprng.action6.c03e02datajdbc.data;

import learn.sprng.action6.c03e02datajdbc.Ingredient;

import java.util.Optional;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
