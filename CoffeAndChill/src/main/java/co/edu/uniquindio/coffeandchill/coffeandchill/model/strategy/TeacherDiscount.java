package co.edu.uniquindio.coffeandchill.coffeandchill.model.strategy;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.Client;
import co.edu.uniquindio.coffeandchill.coffeandchill.model.Teacher;

public class TeacherDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(double totalAmount, Client client) {
        if(client instanceof Teacher) {
            double discountPercentage = 0.15;
            double discountAmount = totalAmount * discountPercentage;
            double total = totalAmount - discountAmount;
            return total;
        }
        return totalAmount;
    }

}
