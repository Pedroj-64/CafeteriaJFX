package co.edu.uniquindio.coffeandchill.coffeandchill.controller;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.*;
import co.edu.uniquindio.coffeandchill.coffeandchill.model.strategy.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMenuController {
    private CoffeeShop coffeeShop;
    private Order currentOrder;
    private List<Product> selectedProducts;

    public OrderMenuController() {
        this.coffeeShop = CoffeeShop.getInstance();
        this.selectedProducts = new ArrayList<>();
    }

    public List<Food> getFoodList() {
        return coffeeShop.getMenu().getFoods();
    }

    public List<Drink> getDrinkList() {
        return coffeeShop.getMenu().getDrinks();
    }

    public List<Combo> getComboList() {
        return coffeeShop.getMenu().getCombos();
    }

    public List<Client> getCustomerList() {
        return coffeeShop.getCustomers();
    }

    public void startNewOrder(Client client) {
        currentOrder = new Order();
        currentOrder.setClient(client);
        currentOrder.setProducts(new ArrayList<>());
        selectedProducts.clear();
    }

    public void addProductToOrder(Product product) {
        if (currentOrder != null) {
            selectedProducts.add(product);
            if (product instanceof Food) {
                if (currentOrder.getFoodItems() == null) {
                    currentOrder.setFoodItems(new ArrayList<>());
                }
                currentOrder.getFoodItems().add((Food) product);
            } else if (product instanceof Drink) {
                if (currentOrder.getDrinkItems() == null) {
                    currentOrder.setDrinkItems(new ArrayList<>());
                }
                currentOrder.getDrinkItems().add((Drink) product);
            } else if (product instanceof Combo) {
                if (currentOrder.getComboItems() == null) {
                    currentOrder.setComboItems(new ArrayList<>());
                }
                currentOrder.getComboItems().add((Combo) product);
            }
            currentOrder.setSubTotal(currentOrder.calculateSubTotal());
        }
    }

    public void removeProductFromOrder(Product product) {
        if (currentOrder != null) {
            selectedProducts.remove(product);
            if (product instanceof Food) {
                currentOrder.getFoodItems().remove(product);
            } else if (product instanceof Drink) {
                currentOrder.getDrinkItems().remove(product);
            } else if (product instanceof Combo) {
                currentOrder.getComboItems().remove(product);
            }
            currentOrder.setSubTotal(currentOrder.calculateSubTotal());
        }
    }

    public Order finishOrder() {
        if (currentOrder != null && !selectedProducts.isEmpty()) {
            BillEmitter billEmitter = new BillEmitter();
            billEmitter.setClient(currentOrder.getClient());
            double total = billEmitter.calculateTotal(currentOrder);
            currentOrder.setTotal(total);
            Order completedOrder = currentOrder;
            currentOrder = null;
            selectedProducts.clear();
            return completedOrder;
        }
        return null;
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public double getCurrentTotal() {
        return currentOrder != null ? currentOrder.getSubTotal() : 0.0;
    }

    public String getRecommendations(Client client) {
        if (client == null) return "";
        
        StringBuilder recommendations = new StringBuilder();
        recommendations.append("Recomendaciones personalizadas para ti:\n\n");

        FoodRecommends foodRecommends = new FoodRecommends();
        DrinksRecommends drinksRecommends = new DrinksRecommends();
        ComboRecommends comboRecommends = new ComboRecommends();

        recommendations.append("Comidas:\n");
        recommendations.append(foodRecommends.recommends(client)).append("\n\n");
        
        recommendations.append("Bebidas:\n");
        recommendations.append(drinksRecommends.recommends(client)).append("\n\n");
        
        recommendations.append("Combos:\n");
        recommendations.append(comboRecommends.recommends(client));

        return recommendations.toString();
    }
}
