package co.edu.uniquindio.coffeandchill.coffeandchill.model.strategy;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.Client;
import co.edu.uniquindio.coffeandchill.coffeandchill.model.CoffeeShop;
import co.edu.uniquindio.coffeandchill.coffeandchill.model.Combo;
import co.edu.uniquindio.coffeandchill.coffeandchill.model.Food;
import co.edu.uniquindio.coffeandchill.coffeandchill.model.Order;

public class ComboRecommends implements RecommendsStrategy {

        /**
     * Recomienda comidas al cliente según el tipo más frecuente en sus órdenes y el
     * menú del CoffeeShop.
     */
    public List<String> recommends(Client client) {

        List<Combo> comboOrdered = new ArrayList<>();
        Combo mostFrequentCombo = null;
        int maxCount = 0;
        for (Order order : client.getOrders()) {
            for (Object foodItems : order.getItems()) {
                if (foodItems instanceof Combo) {
                    comboOrdered.add((Combo) foodItems);
                }
            }
        }
        for (int i = 0; i < comboOrdered.size(); i++) {
            int count = 0;
            for (int j = 0; j < comboOrdered.size(); j++) {
                if (comboOrdered.get(i).equals(comboOrdered.get(j))) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostFrequentCombo = comboOrdered.get(i);
            }
        }

        List<String> recommendCombos = new ArrayList<>();
        List<Combo> menuCombos = CoffeeShop.getInstance().getMenu().getCombos();
        for (Combo combo : menuCombos) {

            if (combo.getType().equals(mostFrequentCombo.getType())) {
                recommendCombos.add(combo.getName());

            }

        }

        return recommendCombos;
    }

}
