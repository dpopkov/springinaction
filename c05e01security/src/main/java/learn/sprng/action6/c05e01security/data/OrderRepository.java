package learn.sprng.action6.c05e01security.data;

import learn.sprng.action6.c05e01security.ShaurmaOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<ShaurmaOrder, Long> {
}
