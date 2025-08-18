package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.strategy.DiscountStrategy;
import co.edu.uniquindio.coffeandchill.coffeandchill.service.BillService;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BillEmitter implements BillService {

    private DiscountStrategy discountStrategy;
    private Client client;
    private double totalAmount;

    public double calculateDiscount(Client client, double totalAmount) {
        double discountedPrice = client.applyDiscount(totalAmount);
        return discountedPrice;
    }

    @Override
    public double calculateTotal(Order order) {
        double total = order.calculateSubTotal();
        total = calculateDiscount(client, total);
        return total;
    }



}
