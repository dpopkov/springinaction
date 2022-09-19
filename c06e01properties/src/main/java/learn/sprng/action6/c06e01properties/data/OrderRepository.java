package learn.sprng.action6.c06e01properties.data;

import learn.sprng.action6.c06e01properties.ShaurmaOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<ShaurmaOrder, Long> {
}
