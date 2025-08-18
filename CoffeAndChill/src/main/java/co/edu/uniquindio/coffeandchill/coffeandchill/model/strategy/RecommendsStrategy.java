package co.edu.uniquindio.coffeandchill.coffeandchill.model.strategy;

import java.util.List;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.Client;

public interface RecommendsStrategy{

    public List<String> recommends(Client client);


}
