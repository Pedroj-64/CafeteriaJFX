package co.edu.uniquindio.coffeandchill.coffeandchill.viewController;

import co.edu.uniquindio.coffeandchill.coffeandchill.App;
import co.edu.uniquindio.coffeandchill.coffeandchill.controller.OrderMenuController;
import co.edu.uniquindio.coffeandchill.coffeandchill.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;

public class OrderMenuViewController {

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_finish;

    @FXML
    private Button btn_new_order;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_remove;

    @FXML
    private ComboBox<Client> cmb_customers;

    @FXML
    private Label lbl_total;

    @FXML
    private ListView<Combo> lst_combos;

    @FXML
    private ListView<Product> lst_current_order;

    @FXML
    private ListView<Drink> lst_drinks;

    @FXML
    private ListView<Food> lst_food;

    @FXML
    private TextArea txt_recommendations;

    private OrderMenuController controller = new OrderMenuController();

    @FXML
    public void initialize() {
        setupLists();
        setupListeners();
        updateControls(false);
    }

    private void setupLists() {
        lst_food.setItems(FXCollections.observableArrayList(controller.getFoodList()));
        lst_drinks.setItems(FXCollections.observableArrayList(controller.getDrinkList()));
        lst_combos.setItems(FXCollections.observableArrayList(controller.getComboList()));
        cmb_customers.setItems(FXCollections.observableArrayList(controller.getCustomerList()));
    }

    private void setupListeners() {
        cmb_customers.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txt_recommendations.setText(controller.getRecommendations(newValue));
            }
        });
    }

    private void updateControls(boolean orderActive) {
        btn_add.setDisable(!orderActive);
        btn_remove.setDisable(!orderActive);
        btn_finish.setDisable(!orderActive);
        btn_new_order.setDisable(orderActive);
        cmb_customers.setDisable(orderActive);
    }

    private void updateCurrentOrder() {
        lst_current_order.setItems(FXCollections.observableArrayList(controller.getSelectedProducts()));
        updateTotal();
    }

    private void updateTotal() {
        lbl_total.setText(String.format("Total: $%.2f", controller.getCurrentTotal()));
    }

    @FXML
    void addToOrder(ActionEvent event) {
        Product selectedProduct = null;

        if (lst_food.getSelectionModel().getSelectedItem() != null) {
            selectedProduct = lst_food.getSelectionModel().getSelectedItem();
        } else if (lst_drinks.getSelectionModel().getSelectedItem() != null) {
            selectedProduct = lst_drinks.getSelectionModel().getSelectedItem();
        } else if (lst_combos.getSelectionModel().getSelectedItem() != null) {
            selectedProduct = lst_combos.getSelectionModel().getSelectedItem();
        }

        if (selectedProduct != null) {
            controller.addProductToOrder(selectedProduct);
            updateCurrentOrder();
            clearSelections();
        } else {
            App.showAlert("Error", "Por favor seleccione un producto para agregar", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void finishOrder(ActionEvent event) {
        if (controller.getSelectedProducts().isEmpty()) {
            App.showAlert("Error", "No se puede finalizar una orden vacía", Alert.AlertType.ERROR);
            return;
        }

        Order completedOrder = controller.finishOrder();
        if (completedOrder != null) {
            updateCurrentOrder();
            updateControls(false);
            App.showAlert("Éxito", "Orden finalizada con éxito", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void newOrder(ActionEvent event) {
        Client selectedClient = cmb_customers.getValue();
        if (selectedClient == null) {
            App.showAlert("Error", "Por favor seleccione un cliente", Alert.AlertType.ERROR);
            return;
        }

        controller.startNewOrder(selectedClient);
        updateCurrentOrder();
        updateControls(true);
    }

    @FXML
    void removeOrder(ActionEvent event) {
        Product selectedProduct = lst_current_order.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            controller.removeProductFromOrder(selectedProduct);
            updateCurrentOrder();
        } else {
            App.showAlert("Error", "Por favor seleccione un producto para eliminar", Alert.AlertType.ERROR);
        }
    }

    private void clearSelections() {
        lst_food.getSelectionModel().clearSelection();
        lst_drinks.getSelectionModel().clearSelection();
        lst_combos.getSelectionModel().clearSelection();
    }

    @FXML
    void goBack(ActionEvent event) {
        App.loadScene("StartMenu", 800, 600);
    }

}
