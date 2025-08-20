package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.enumeration.FoodType;
import lombok.Data;

@Data
public class Food {

    private String name;
    private double price;
    private String description;
    private FoodType type;

}
