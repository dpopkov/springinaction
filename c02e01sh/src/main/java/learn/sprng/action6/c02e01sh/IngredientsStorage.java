package learn.sprng.action6.c02e01sh;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class IngredientsStorage {
    private final List<Ingredient> ingredients;
    private final Map<String, Ingredient> ingredientMap;

    public IngredientsStorage() {
        ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );
        ingredientMap = new HashMap<>();
        ingredients.forEach(ingredient-> ingredientMap.put(ingredient.getId(), ingredient));
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public Map<String, Ingredient> getIngredientMap() {
        return ingredientMap;
    }
}
