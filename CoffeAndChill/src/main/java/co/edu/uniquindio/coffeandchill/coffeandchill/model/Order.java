package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class Order {

    private String id;
    private Client client;
    private LocalDateTime orderDate;
    private List<Food> foodItems;
    private List<Drink> drinkItems;
    private List<Combo> comboItems;
    private List<Product> products;
    private double subTotal;
    private double discount;
    private double iva;
    private double total;

    public double calculateSubTotal() {

        subTotal = 0;

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
