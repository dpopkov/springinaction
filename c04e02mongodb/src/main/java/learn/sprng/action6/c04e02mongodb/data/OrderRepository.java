package learn.sprng.action6.c04e02mongodb.data;

import learn.sprng.action6.c04e02mongodb.ShaurmaOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<ShaurmaOrder, String> {
}
