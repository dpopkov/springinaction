package learn.sprng.action6.c04e01cassandra.data;

import learn.sprng.action6.c04e01cassandra.ShaurmaOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<ShaurmaOrder, UUID> {
}
