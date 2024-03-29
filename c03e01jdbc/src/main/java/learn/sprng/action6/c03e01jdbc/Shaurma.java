package learn.sprng.action6.c03e01jdbc;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Shaurma {

    private Long id;
    private Date createdAd;
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @NotNull
    @Size(min = 1, message = "You must choose at least one ingredient")
    private List<IngredientRef> ingredients;

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(new IngredientRef(ingredient.getId()));
    }
}
