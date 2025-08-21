package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Menu {

    private List<Drink> drinks;
    private List<Food> foods;
    private List<Combo> combos;

}
