package learn.sprng.action6.c05e01security.web;

import learn.sprng.action6.c05e01security.Ingredient;
import learn.sprng.action6.c05e01security.data.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find ingredient by id: " + id));
    }
}
