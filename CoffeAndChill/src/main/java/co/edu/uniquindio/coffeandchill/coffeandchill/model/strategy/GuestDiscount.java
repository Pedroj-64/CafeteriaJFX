package co.edu.uniquindio.coffeandchill.coffeandchill.model.strategy;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.Client;
import co.edu.uniquindio.coffeandchill.coffeandchill.model.Guest;

public class GuestDiscount  implements DiscountStrategy {

    @Override
    public double applyDiscount(double totalAmount,Client client) {
        if(client instanceof Guest) {
            double discountPercentage = 0.3;
            double discountAmount = totalAmount * discountPercentage;
            double total = totalAmount - discountAmount;
            return total;
        }
        return totalAmount;
    }

}
