package co.edu.uniquindio.coffeandchill.coffeandchill.model.strategy;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.*;

public class DrinksRecommends implements RecommendsStrategy {

    /**
     * Recomienda bebidas al cliente según el tipo más frecuente en sus órdenes y el
     * menú del CoffeeShop.
     */
    @Override
    public List<String> recommends(Client client) {

        List<Drink> drinkOrdered = new ArrayList<>();
        Drink mostFrequentDrink = null;
        int maxCount = 0;
        for (Order order : client.getOrders()) {
            for (Product drinkItems : order.getProducts()) {
                if (drinkItems instanceof Drink) {
                    drinkOrdered.add((Drink) drinkItems);
                }
            }
        }
        for (int i = 0; i < drinkOrdered.size(); i++) {
            int count = 0;
            for (int j = 0; j < drinkOrdered.size(); j++) {
                if (drinkOrdered.get(i).equals(drinkOrdered.get(j))) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostFrequentDrink = drinkOrdered.get(i);
            }
        }

        List<String> recommendDrinks = new ArrayList<>();
        List<Drink> menuDrinks = CoffeeShop.getInstance().getMenu().getDrinks();
        for (Drink drink : menuDrinks) {

            if (drink.getType().equals(mostFrequentDrink.getType())) {
                recommendDrinks.add(drink.getName());

            }

        }

        return recommendDrinks;
    }

}
