package learn.sprng.action6.c03e03datajpa;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Shaurma {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt = new Date();
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @NotNull
    @Size(min = 1, message = "You must choose at least one ingredient")
    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
}
