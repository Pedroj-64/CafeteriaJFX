package co.edu.uniquindio.coffeandchill.coffeandchill.controller;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.*;
import co.edu.uniquindio.coffeandchill.coffeandchill.model.enumeration.*;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductManagementController {
    private final CoffeeShop coffeeShop;

    public ProductManagementController() {
        this.coffeeShop = CoffeeShop.getInstance();
    }

    public void addDrink(String name, double price, DrinksType type,String description) {
        Drink drink = new Drink();
        drink.setName(name);
        drink.setPrice(price);
        drink.setType(type);
        drink.setDescription(description);
        coffeeShop.getMenu().getDrinks().add(drink);
    }

    public void addFood(String name, double price, FoodType type,String description) {
        Food food = new Food();
        food.setName(name);
        food.setPrice(price);
        food.setType(type);
        food.setDescription(description);
        coffeeShop.getMenu().getFoods().add(food);
    }

    public void addCombo(String name, double price, ComboType type, Food food, Drink drink,String description) {
        Combo combo = new Combo();
        combo.setName(name);
        combo.setPrice(price);
        combo.setType(type);
        combo.setFood(food);
        combo.setDrink(drink);
        combo.setDescription(description);
        coffeeShop.getMenu().getCombos().add(combo);
    }

    public void deleteProduct(Product product) {
        if (product instanceof Drink) {
            coffeeShop.getMenu().getDrinks().remove(product);
        } else if (product instanceof Food) {
            coffeeShop.getMenu().getFoods().remove(product);
        } else if (product instanceof Combo) {
            coffeeShop.getMenu().getCombos().remove(product);
        }
    }

    public List<Drink> getAllDrinks() {
        return coffeeShop.getMenu().getDrinks();
    }

    public List<Food> getAllFoods() {
        return coffeeShop.getMenu().getFoods();
    }

    public List<Combo> getAllCombos() {
        return coffeeShop.getMenu().getCombos();
    }

    public void updateProduct(Product product) {
       
    }
}
