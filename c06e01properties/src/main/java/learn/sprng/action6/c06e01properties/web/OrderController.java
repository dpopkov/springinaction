package learn.sprng.action6.c06e01properties.web;

import learn.sprng.action6.c06e01properties.AppUser;
import learn.sprng.action6.c06e01properties.ShaurmaOrder;
import learn.sprng.action6.c06e01properties.data.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("shaurmaOrder")
public class OrderController {

    private static final String ORDER_FORM_VIEW_NAME = "orderForm";

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String showOrderForm(ShaurmaOrder order,
                                @AuthenticationPrincipal AppUser user) {
        order.setDeliveryName(user.getUsername());
        order.setDeliveryStreet(user.getDeliveryStreet());
        order.setDeliveryCity(user.getDeliveryCity());
        order.setDeliveryState(user.getDeliveryState());
        order.setDeliveryZip(user.getDeliveryZip());
        return ORDER_FORM_VIEW_NAME;
    }

    @PostMapping
    public String processOrder(@Valid ShaurmaOrder order,
                               Errors errorsCapturer,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal AppUser user) {
        if (errorsCapturer.hasErrors()) {
            return ORDER_FORM_VIEW_NAME;
        }
        order.setUser(user);
        orderRepository.save(order);
        log.info("Order saved: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
