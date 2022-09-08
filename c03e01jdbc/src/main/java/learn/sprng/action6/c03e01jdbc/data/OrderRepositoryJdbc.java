package learn.sprng.action6.c03e01jdbc.data;

import learn.sprng.action6.c03e01jdbc.IngredientRef;
import learn.sprng.action6.c03e01jdbc.Shaurma;
import learn.sprng.action6.c03e01jdbc.ShaurmaOrder;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class OrderRepositoryJdbc implements OrderRepository {

    private final JdbcOperations jdbcOperations;

    public OrderRepositoryJdbc(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public ShaurmaOrder save(ShaurmaOrder order) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "INSERT INTO Shaurma_Order"
                        + "(delivery_Name, delivery_Street, delivery_City, delivery_State, delivery_Zip, "
                        + "cc_number, cc_expiration, cc_cvv, placed_at) "
                        + "VALUES ( ?,?,?,?,?,?,?,?,? )",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(new Date());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(order.getDeliveryName(), order.getDeliveryStreet(), order.getDeliveryCity(),
                        order.getDeliveryState(), order.getDeliveryZip(),
                        order.getCcNumber(), order.getCcExpiration(), order.getCcCVV(), order.getPlacedAt()));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        order.setId(orderId);

        List<Shaurma> shaurmas = order.getShaurmas();
        int i = 0;
        for (Shaurma sh : shaurmas) {
            saveShaurma(orderId, i++, sh);
        }
        return order;
    }

    private long saveShaurma(long orderId, int orderKey, Shaurma shaurma) {
        shaurma.setCreatedAd(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "INSERT INTO Shaurma (name, shaurma_order, shaurma_order_key, created_at) "
                + "VALUES (?, ?, ?, ?)",
                Types.VARCHAR, Type.LONG, Type.LONG, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
                shaurma.getName(), orderId, orderKey, shaurma.getCreatedAd()
        ));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long shaurmaId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        shaurma.setId(shaurmaId);

        saveIngredientRefs(shaurmaId, shaurma.getIngredients());
        return shaurmaId;
    }

    private void saveIngredientRefs(long shaurmaId, List<IngredientRef> ingredientRefs) {
        int ingredientIndex = 0;
        for (IngredientRef ref : ingredientRefs) {
            jdbcOperations.update(
                    "INSERT INTO Ingredient_Ref (ingredient_id, shaurma_id, ingredient_index) "
                    + "VALUES (?, ?, ?)",
                    ref.getIngredientId(), shaurmaId, ingredientIndex++
            );
        }
    }
}
