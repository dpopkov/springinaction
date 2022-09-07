package learn.sprng.action6.c02e01sh;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShaurmaOrder {

    /* Delivery information */
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    /* Payment information */
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private List<Shaurma> shaurmas = new ArrayList<>();

    public void addShaurma(Shaurma shaurma) {
        shaurmas.add(shaurma);
    }
}
