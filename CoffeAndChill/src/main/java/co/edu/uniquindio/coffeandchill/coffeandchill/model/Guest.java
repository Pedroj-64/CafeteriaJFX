package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.strategy.DiscountStrategy;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Guest extends Client {

    private DiscountStrategy discountStrategy;

    @Override
    public double applyDiscount(double totalAmount) {
        return discountStrategy.applyDiscount(totalAmount, this);
    }

}

