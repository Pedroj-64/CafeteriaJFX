package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import java.util.List;

import lombok.Data;

@Data
public abstract class Client {

    private String name;
    private int id;
    private List<Order> orders;

    public abstract double applyDiscount(double totalAmount);

}
