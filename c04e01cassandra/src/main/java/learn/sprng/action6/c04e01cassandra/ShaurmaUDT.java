package learn.sprng.action6.c04e01cassandra;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

@Data
@UserDefinedType("shaurma")
public class ShaurmaUDT {
    private final String name;
    private final List<IngredientUDT> ingredients;
}
