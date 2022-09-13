package learn.sprng.action6.c04e02mongodb.data;

import learn.sprng.action6.c04e02mongodb.ShaurmaOrder;

public interface OrderRepository {

    ShaurmaOrder save(ShaurmaOrder order);
}
