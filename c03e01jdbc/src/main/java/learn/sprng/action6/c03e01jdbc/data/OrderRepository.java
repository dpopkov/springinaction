package learn.sprng.action6.c03e01jdbc.data;

import learn.sprng.action6.c03e01jdbc.ShaurmaOrder;

public interface OrderRepository {

    ShaurmaOrder save(ShaurmaOrder order);
}
