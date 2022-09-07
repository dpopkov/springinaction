package learn.sprng.action6.c02e01sh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static learn.sprng.action6.c02e01sh.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("shaurmaOrder")  // <-- shaurmaOrder can span multiple requests
public class DesignShaurmaController {

    private final IngredientsStorage ingredientsStorage;

    public DesignShaurmaController(IngredientsStorage ingredientsStorage) {
        this.ingredientsStorage = ingredientsStorage;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = ingredientsStorage.getIngredients();
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(ing -> ing.getType().equals(type)).collect(Collectors.toList());
    }

    @ModelAttribute(name = "shaurmaOrder")
    public ShaurmaOrder order() {
        return new ShaurmaOrder();
    }

    @ModelAttribute(name = "shaurma")
    public Shaurma shaurma() {
        return new Shaurma();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processDesignForm(Shaurma shaurma,
                                    @ModelAttribute ShaurmaOrder shaurmaOrder) {
        shaurmaOrder.addShaurma(shaurma);
        log.info("Processing shaurma: {}", shaurma);
        return "redirect:/orders/current";
    }
}
