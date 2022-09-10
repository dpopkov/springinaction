package learn.sprng.action6.c03e03datajpa.data;

import learn.sprng.action6.c03e03datajpa.Ingredient;
import learn.sprng.action6.c03e03datajpa.Shaurma;
import learn.sprng.action6.c03e03datajpa.ShaurmaOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static learn.sprng.action6.c03e03datajpa.Ingredient.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryIT {
    private static final String DELIVERY_NAME = "Test Delivery name";
    private static final String DELIVERY_STREET = "4321 Test Lane";
    private static final String DELIVERY_CITY = "Testcity";
    private static final String DELIVERY_STATE = "CO";
    private static final String DELIVERY_ZIP = "12345";
    private static final String CC_NUMBER = "4797033900585183";
    private static final String CC_EXPIRATION = "10/22";
    private static final String CC_CVV = "123";

    @Autowired
    OrderRepository orderRepository;

    @Test
    void saveOrderWithTwoShaurmas() {
        Shaurma shaurma1 = new Shaurma();
        shaurma1.setName("Sharuma-1");
        shaurma1.addIngredient(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
        shaurma1.addIngredient(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
        Shaurma shaurma2 = new Shaurma();
        shaurma2.addIngredient(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
        shaurma2.addIngredient(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
        shaurma2.setName("Sharuma-2");
        ShaurmaOrder order = new ShaurmaOrder();
        order.setDeliveryName(DELIVERY_NAME);
        order.setDeliveryStreet(DELIVERY_STREET);
        order.setDeliveryCity(DELIVERY_CITY);
        order.setDeliveryState(DELIVERY_STATE);
        order.setDeliveryZip(DELIVERY_ZIP);
        order.setCcNumber(CC_NUMBER);
        order.setCcExpiration(CC_EXPIRATION);
        order.setCcCVV(CC_CVV);
        order.addShaurma(shaurma1);
        order.addShaurma(shaurma2);

        ShaurmaOrder saved = orderRepository.save(order);
        assertNotNull(saved.getId());

        ShaurmaOrder fetched = orderRepository.findById(saved.getId()).orElseThrow();
        assertEquals(DELIVERY_NAME, fetched.getDeliveryName());
        assertEquals(DELIVERY_STREET, fetched.getDeliveryStreet());
        assertEquals(DELIVERY_CITY, fetched.getDeliveryCity());
        assertEquals(DELIVERY_STATE, fetched.getDeliveryState());
        assertEquals(DELIVERY_ZIP, fetched.getDeliveryZip());
        assertEquals(CC_NUMBER, fetched.getCcNumber());
        assertEquals(CC_EXPIRATION, fetched.getCcExpiration());
        assertEquals(CC_CVV, fetched.getCcCVV());

        List<Shaurma> shaurmas = fetched.getShaurmas();
        assertThat(shaurmas.size()).isEqualTo(2);
        assertThat(shaurmas).containsExactlyInAnyOrder(shaurma1, shaurma2);
    }
}
