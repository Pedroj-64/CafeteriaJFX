package co.edu.uniquindio.coffeandchill.coffeandchill.model.strategy;

import java.util.List;
import co.edu.uniquindio.coffeandchill.coffeandchill.model.*;

import java.util.ArrayList;

public class FoodRecommends implements RecommendsStrategy {

    /**
     * Recomienda comidas al cliente según el tipo más frecuente en sus órdenes y el
     * menú del CoffeeShop.
     */
    public List<String> recommends(Client client) {

        List<Food> foodsOrdered = new ArrayList<>();
        Food mostFrequentFood = null;
        int maxCount = 0;
        for (Order order : client.getOrders()) {
            for (Product foodItems : order.getProducts()) {
                if (foodItems instanceof Food) {
                    foodsOrdered.add((Food) foodItems);
                }
            }
        }
        for (int i = 0; i < foodsOrdered.size(); i++) {
            int count = 0;
            for (int j = 0; j < foodsOrdered.size(); j++) {
                if (foodsOrdered.get(i).equals(foodsOrdered.get(j))) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostFrequentFood = foodsOrdered.get(i);
            }
        }

        List<String> recommendFoods = new ArrayList<>();
        List<Food> menuFoods = CoffeeShop.getInstance().getMenu().getFoods();
        for (Food food : menuFoods) {

            if (food.getType().equals(mostFrequentFood.getType())) {
                recommendFoods.add(food.getName());

            }

        }

        return recommendFoods;
    }

}
