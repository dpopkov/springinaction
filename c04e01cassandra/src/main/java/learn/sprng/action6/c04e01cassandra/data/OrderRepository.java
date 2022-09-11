package learn.sprng.action6.c04e01cassandra.data;

import learn.sprng.action6.c04e01cassandra.ShaurmaOrder;

public interface OrderRepository {

    ShaurmaOrder save(ShaurmaOrder order);
}
