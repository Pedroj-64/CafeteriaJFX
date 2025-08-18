package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Order {

    private List<Food> foodItems;
    private List<Drink> drinkItems;
    private List<Combo> comboItems;
    private List<List<Object>> items = new ArrayList<>();

    public double calculateSubTotal() {

        double subTotal = 0;

        for (Food food : foodItems) {
            subTotal += food.getPrice();
        }

        for (Drink drink : drinkItems) {
            subTotal += drink.getPrice();
        }

        for (Combo combo : comboItems) {
            subTotal += combo.getPrice();
        }

        return subTotal;
    }

    

}
