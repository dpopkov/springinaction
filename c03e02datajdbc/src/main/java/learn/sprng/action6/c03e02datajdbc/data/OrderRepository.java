package learn.sprng.action6.c03e02datajdbc.data;

import learn.sprng.action6.c03e02datajdbc.ShaurmaOrder;

public interface OrderRepository {

    ShaurmaOrder save(ShaurmaOrder order);
}
