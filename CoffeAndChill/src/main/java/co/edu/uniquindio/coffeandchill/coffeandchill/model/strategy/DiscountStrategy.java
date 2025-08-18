package co.edu.uniquindio.coffeandchill.coffeandchill.model.strategy;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.Client;

public interface DiscountStrategy {

    double applyDiscount(double price, Client client);

}
