package co.edu.uniquindio.coffeandchill.coffeandchill.service;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.Order;
import java.util.List;

/**
 * Interface for report generation services.
 * This interface ensures scalability by allowing different types of reports
 * to be implemented without modifying existing code.
 */
public interface ReportService {
    /**
     * Generates a report from a list of orders
     * @param orders List of orders to include in the report
     */
    void generateReport(List<Order> orders);

    /**
     * Calculates total sales from the orders
     * @return Total sales amount
     */
    double calculateTotalSales();

    /**
     * Calculates total discounts applied
     * @return Total discounts amount
     */
    double calculateTotalDiscounts();

    /**
     * Calculates total IVA (tax)
     * @return Total IVA amount
     */
    double calculateTotalIVA();

    /**
     * Gets the final total including all taxes and discounts
     * @return Final total amount
     */
    double getFinalTotal();
}
