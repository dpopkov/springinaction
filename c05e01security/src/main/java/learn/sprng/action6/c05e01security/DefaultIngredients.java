package learn.sprng.action6.c05e01security;

import java.util.*;

import static learn.sprng.action6.c05e01security.Ingredient.*;

public class DefaultIngredients {

    private static final List<Ingredient> ingredients;
    private static final Map<String, Ingredient> ingredientMap = new LinkedHashMap<>();

    static {
        ingredients = List.of(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );
        ingredients.forEach(ingredient -> ingredientMap.put(ingredient.getId(), ingredient));
    }

    public static List<Ingredient> getList() {
        return ingredients;
    }

    public static Ingredient getById(String id) {
        return ingredientMap.get(id);
    }

    public static Iterable<String> getIds() {
        return ingredientMap.keySet();
    }
}
