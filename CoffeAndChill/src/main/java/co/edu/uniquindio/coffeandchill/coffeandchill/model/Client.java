package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import java.util.List;
import co.edu.uniquindio.coffeandchill.coffeandchill.model.strategy.DiscountStrategy;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Client {

    private String name;
    private int id;
    private List<Order> orders;
    protected DiscountStrategy discountStrategy;

    public abstract double applyDiscount(double totalAmount);

}
