package learn.sprng.action6.c03e02datajdbc;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class IngredientRef {

    private final String ingredientId;
}
