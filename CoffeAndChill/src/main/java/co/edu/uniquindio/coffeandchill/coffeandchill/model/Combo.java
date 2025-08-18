package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import lombok.Data;

@Data
public class Combo {

    private String descripcion;
    private Food food;
    private Drink drink;
    private double price;

}
