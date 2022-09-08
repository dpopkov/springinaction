package learn.sprng.action6.c03e01jdbc.data;

import learn.sprng.action6.c03e01jdbc.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static learn.sprng.action6.c03e01jdbc.Ingredient.Type;

@Repository
public class IngredientRepositoryJdbc implements IngredientRepository {
    private static final String TABLE_NAME = "ingredient";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String TYPE_COLUMN = "type";
    private static final String COLUMN_NAMES = ID_COLUMN + ", " + NAME_COLUMN + ", " + TYPE_COLUMN;

    private final JdbcTemplate jdbcTemplate;

    public IngredientRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        String sql = "SELECT " + COLUMN_NAMES + " FROM " + TABLE_NAME;
        return jdbcTemplate.query(sql, this::mapRow);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        String sql = "SELECT " + COLUMN_NAMES + " FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = ?";
        List<Ingredient> list = jdbcTemplate.query(sql, this::mapRow, id);
        return list.isEmpty() ?
                Optional.empty() :
                Optional.of(list.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        String sql = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAMES + ") VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
        return ingredient;
    }

    private Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Ingredient(
                rs.getString(ID_COLUMN),
                rs.getString(NAME_COLUMN),
                Type.valueOf(rs.getString(TYPE_COLUMN).toUpperCase())
        );
    }
}
