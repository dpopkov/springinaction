package learn.sprng.action6.c04e01cassandra.web;

import learn.sprng.action6.c04e01cassandra.Ingredient;
import learn.sprng.action6.c04e01cassandra.IngredientUDT;
import learn.sprng.action6.c04e01cassandra.data.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StringToIngredientConverter implements Converter<String, IngredientUDT> {

    private final IngredientRepository ingredientRepository;

    public StringToIngredientConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientUDT convert(String id) {
        Optional<Ingredient> byId = ingredientRepository.findById(id);
        if (byId.isEmpty()) {
            return null;
        }
        return byId.map(ingredient -> new IngredientUDT(ingredient.getName(), ingredient.getType())).get();
    }
}
