package learn.sprng.action6.c03e03datajpa.web;

import learn.sprng.action6.c03e03datajpa.Ingredient;
import learn.sprng.action6.c03e03datajpa.Shaurma;
import learn.sprng.action6.c03e03datajpa.ShaurmaOrder;
import learn.sprng.action6.c03e03datajpa.data.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static learn.sprng.action6.c03e03datajpa.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("shaurmaOrder")  // <-- shaurmaOrder can span multiple requests
public class DesignShaurmaController {

    private static final String DESIGN_FORM_VIEW_NAME = "design";

    private final IngredientRepository ingredientRepository;

    public DesignShaurmaController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(ing -> ing.getType().equals(type))
                .collect(Collectors.toList());
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
        return DESIGN_FORM_VIEW_NAME;
    }

    @PostMapping
    public String processDesignForm(@Valid Shaurma shaurma,
                                    Errors errorsCapturer,
                                    @ModelAttribute ShaurmaOrder shaurmaOrder) {
        if (errorsCapturer.hasErrors()) {
            return DESIGN_FORM_VIEW_NAME;
        }
        shaurmaOrder.addShaurma(shaurma);
        log.info("Added shaurma to order: {}", shaurma);
        return "redirect:/orders/current";
    }
}
