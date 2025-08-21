package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.enumeration.FoodType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Food extends Product {

    private FoodType type;

}
