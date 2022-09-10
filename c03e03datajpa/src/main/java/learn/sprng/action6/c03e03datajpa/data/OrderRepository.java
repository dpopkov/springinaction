package learn.sprng.action6.c03e03datajpa.data;

import learn.sprng.action6.c03e03datajpa.ShaurmaOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<ShaurmaOrder, Long> {
}
