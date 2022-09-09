package learn.sprng.action6.c03e02datajdbc.data;

import learn.sprng.action6.c03e02datajdbc.ShaurmaOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<ShaurmaOrder, Long> {
}
