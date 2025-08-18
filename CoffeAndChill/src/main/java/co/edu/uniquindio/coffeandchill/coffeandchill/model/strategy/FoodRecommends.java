package co.edu.uniquindio.coffeandchill.coffeandchill.model.strategy;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.*;

import java.util.Map;
import java.util.HashMap;

public class FoodRecommends implements RecommendsStrategy{

    /**
     * Recomienda comidas al cliente según las más frecuentes en sus órdenes y el menú del CoffeeShop.
     * @param client El cliente al que se le recomienda.
     * @param coffeeShop El CoffeeShop para consultar el menú.
     * @return Lista de nombres de comidas recomendadas.
     */
    public List<String> recommends(Client client) {
        // 1. Obtener todas las comidas que ha pedido el cliente
        List<Food> foodsOrdered = client.getOrders().get(0).getItems().stream()
            .filter(item -> item instanceof Food)
            .map(item -> (Food) item)
            .collect(Collectors.toList());

        // 2. Contar cuántas veces se repite cada comida
        Map<String, Long> foodCount = foodsOrdered.stream()
            .collect(Collectors.groupingBy(Food::getName, Collectors.counting()));

        // 3. Consultar el menú del CoffeeShop
        List<Food> menu = CoffeeShop.getInstance().getMenu().getFoods();

        // 4. Crear lista de recomendaciones basada en las comidas más frecuentes
        List<String> menuRecommendation = menu.stream()
            .filter(food -> foodCount.containsKey(food.getName()))
            .sorted((f1, f2) -> Long.compare(foodCount.get(f2.getName()), foodCount.get(f1.getName())))
            .map(Food::getName)
            .collect(Collectors.toList());


        return menuRecommendation;
    }


}
