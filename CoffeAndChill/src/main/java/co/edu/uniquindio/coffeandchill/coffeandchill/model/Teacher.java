package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.strategy.DiscountStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class Teacher extends Client {

    private DiscountStrategy discountStrategy;

    @Override
    public double applyDiscount(double totalAmount) {
        return discountStrategy.applyDiscount(totalAmount,this);
    }

}
