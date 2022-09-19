package learn.sprng.action6.c06e01properties.data;

import learn.sprng.action6.c06e01properties.AppUser;
import learn.sprng.action6.c06e01properties.ShaurmaOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<ShaurmaOrder, Long> {

    List<ShaurmaOrder> findByUserOrderByPlacedAtDesc(AppUser user, Pageable pageable);
}
