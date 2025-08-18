package co.edu.uniquindio.coffeandchill.coffeandchill.service;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.Order;

public interface BillService {
    
    double calculateTotal(Order order);
} 
