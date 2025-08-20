package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.enumeration.ComboType;
import lombok.Data;

@Data
public class Combo {

    private String name;
    private String descripcion;
    private Food food;
    private Drink drink;
    private double price;
    private ComboType type;

}
