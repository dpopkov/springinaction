package learn.sprng.action6.c03e01jdbc.web;

import learn.sprng.action6.c03e01jdbc.Ingredient;
import learn.sprng.action6.c03e01jdbc.data.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElseThrow();
    }
}
