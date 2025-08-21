package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.strategy.DiscountStrategy;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Student  extends Client{

    private DiscountStrategy discountStrategy;

    @Override
    public double applyDiscount(double totalAmount) {
        return discountStrategy.applyDiscount(totalAmount,this);
    }

}
