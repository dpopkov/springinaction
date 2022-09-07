package learn.sprng.action6.c02e01sh;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final Map<String, Ingredient> ingredientMap;

    public IngredientByIdConverter(IngredientsStorage ingredientsStorage) {
        ingredientMap = ingredientsStorage.getIngredientMap();
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
