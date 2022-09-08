package learn.sprng.action6.c03e01jdbc.web;

import learn.sprng.action6.c03e01jdbc.ShaurmaOrder;
import learn.sprng.action6.c03e01jdbc.data.OrderRepository;
import lombok.extern.slf4j.Slf4j;
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
    public String showOrderForm() {
        return ORDER_FORM_VIEW_NAME;
    }

    @PostMapping
    public String processOrder(@Valid ShaurmaOrder order,
                               Errors errorsCapturer,
                               SessionStatus sessionStatus) {
        if (errorsCapturer.hasErrors()) {
            return ORDER_FORM_VIEW_NAME;
        }
        orderRepository.save(order);
        log.info("Order saved: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
