package learn.sprng.action6.c06e01properties.services;

import learn.sprng.action6.c06e01properties.data.OrderRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final OrderRepository orderRepository;

    public AdminService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
