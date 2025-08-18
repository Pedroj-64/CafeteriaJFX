package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import lombok.*;

@Data
public class CoffeeShop {

    private String name;
    private Menu menu;
    private static CoffeeShop instance;

    private CoffeeShop() {
        
    }

    public static CoffeeShop getInstance() {
        if (instance == null) {
            instance = new CoffeeShop();
        }
        return instance;
    }


}
