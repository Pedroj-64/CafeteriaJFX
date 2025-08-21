package co.edu.uniquindio.coffeandchill.coffeandchill.model;

import co.edu.uniquindio.coffeandchill.coffeandchill.service.ReportService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a daily sales report for the coffee shop.
 * Implements ReportService interface for scalability.
 * Uses Lombok to reduce boilerplate code.
 */
@Getter
@NoArgsConstructor
public class Report implements ReportService {
    private static final double IVA_RATE = 0.19;
    private CoffeeShop coffeeShop;
    private List<Order> orders;
    private LocalDateTime reportDate;
    private BillEmitter billEmitter;
    private double totalSales;
    private double totalDiscounts;
    private double totalIVA;
    private double totalWithIVA;

    {
        this.coffeeShop = CoffeeShop.getInstance();
        this.reportDate = LocalDateTime.now();
        this.billEmitter = new BillEmitter();
        this.orders = new ArrayList<>();
    }

    /**
     * Generates a financial report based on the provided list of orders.
     * Updates all totals including sales, discounts, and taxes.
     *
     * @param orders The list of orders to include in the report
     */
    @Override
    public void generateReport(List<Order> orders) {
        this.orders = orders;
        calculateTotals();
    }

    /**
     * Calculates the total sales amount before discounts and taxes.
     *
     * @return The sum of all orders' subtotals
     */
    @Override
    public double calculateTotalSales() {
        return orders.stream()
                .mapToDouble(Order::calculateSubTotal)
                .sum();
    }

    /**
     * Calculates the total amount of discounts applied across all orders.
     * Uses BillEmitter to apply client-specific discount strategies.
     *
     * @return The total amount of discounts applied
     */
    @Override
    public double calculateTotalDiscounts() {
        return orders.stream()
                .mapToDouble(order -> {
                    billEmitter.setClient(order.getClient());
                    return order.calculateSubTotal() - billEmitter.calculateTotal(order);
                })
                .sum();
    }

    /**
     * Calculates the total IVA (tax) amount for all orders.
     * IVA is calculated after discounts are applied.
     *
     * @return The total IVA amount
     */
    @Override
    public double calculateTotalIVA() {
        return orders.stream()
                .mapToDouble(order -> {
                    billEmitter.setClient(order.getClient());
                    return billEmitter.calculateTotal(order) * IVA_RATE;
                })
                .sum();
    }

    /**
     * Calculates the final total including all discounts and IVA.
     *
     * @return The final amount including all discounts and taxes
     */
    @Override
    public double getFinalTotal() {
        double withDiscounts = orders.stream()
                .mapToDouble(order -> {
                    billEmitter.setClient(order.getClient());
                    return billEmitter.calculateTotal(order);
                })
                .sum();
        return withDiscounts + calculateTotalIVA();
    }

    /**
     * Updates all total amounts in the report.
     * This includes sales, discounts, IVA, and final total.
     */
    private void calculateTotals() {
        totalSales = calculateTotalSales();
        totalDiscounts = calculateTotalDiscounts();
        totalIVA = calculateTotalIVA();
        totalWithIVA = getFinalTotal();
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm:ss");
        StringBuilder report = new StringBuilder();

        // Header
        report.append("╔══════════════════════════════════════════════╗\n");
        report.append("║             COFFEE & CHILL REPORT            ║\n");
        report.append("╠══════════════════════════════════════════════╣\n");
        report.append(String.format("║ Generated: %-35s ║\n", reportDate.format(dateFormat)));
        report.append("╠══════════════════════════════════════════════╣\n");

        // Daily Summary
        report.append("║               DAILY SUMMARY                  ║\n");
        report.append("╟──────────────────────────────────────────────╢\n");
        report.append(String.format("║ Total Orders: %-31d ║\n", orders.size()));
        report.append(String.format("║ Subtotal: $%-33.2f ║\n", totalSales));
        report.append(String.format("║ Discounts: $%-32.2f ║\n", totalDiscounts));
        report.append(String.format("║ IVA (19%%): $%-31.2f ║\n", totalIVA));
        report.append(String.format("║ TOTAL: $%-35.2f ║\n", totalWithIVA));
        report.append("╠══════════════════════════════════════════════╣\n");

        // Sales Details
        report.append("║                ORDER DETAILS                 ║\n");
        report.append("╟──────────────────────────────────────────────╢\n");

        for (Order order : orders) {
            report.append("║ ---------------------------------------- ║\n");
            
            // Food items
            if (!order.getFoodItems().isEmpty()) {
                report.append("║ Food Items:                              ║\n");
                for (Food food : order.getFoodItems()) {
                    report.append(String.format("║   • %-37s ║\n", 
                        food.getName() + " - $" + String.format("%.2f", food.getPrice())));
                }
            }

            // Drink items
            if (!order.getDrinkItems().isEmpty()) {
                report.append("║ Beverages:                               ║\n");
                for (Drink drink : order.getDrinkItems()) {
                    report.append(String.format("║   • %-37s ║\n", 
                        drink.getName() + " - $" + String.format("%.2f", drink.getPrice())));
                }
            }

            // Combo items
            if (!order.getComboItems().isEmpty()) {
                report.append("║ Combo Items:                              ║\n");
                for (Combo combo : order.getComboItems()) {
                    report.append(String.format("║   • %-37s ║\n", 
                        combo.getName() + " - $" + String.format("%.2f", combo.getPrice())));
                }
            }

            report.append("║ ---------------------------------------- ║\n");
            report.append(String.format("║ Order Total: $%-31.2f ║\n", order.calculateSubTotal()));
            report.append("║ ---------------------------------------- ║\n");
        }

        // Footer
        report.append("╚══════════════════════════════════════════════╝\n");

        return report.toString();
    }
}
    
