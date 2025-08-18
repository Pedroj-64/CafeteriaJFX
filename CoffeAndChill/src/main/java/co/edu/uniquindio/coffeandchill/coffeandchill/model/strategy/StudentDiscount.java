package co.edu.uniquindio.coffeandchill.coffeandchill.model.strategy;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.Client;
import co.edu.uniquindio.coffeandchill.coffeandchill.model.Student;

public class StudentDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(double totalAmount, Client client) {

        if(client instanceof Student) {
            double discountPercentage = 0.08;
            double discountAmount = totalAmount * discountPercentage;
            double total = totalAmount - discountAmount;
            return total;
        }
        return totalAmount;

    }

}
