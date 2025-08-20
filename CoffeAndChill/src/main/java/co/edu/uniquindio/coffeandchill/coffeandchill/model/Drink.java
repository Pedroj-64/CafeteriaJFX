package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.enumeration.DrinksType;
import lombok.Data;

@Data
public class Drink {

    private String name;
    private double price;
    private String description;
    private DrinksType type;


}
