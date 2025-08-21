package co.edu.uniquindio.coffeandchill.coffeandchill.viewController;

import co.edu.uniquindio.coffeandchill.coffeandchill.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartMenuViewController {

    @FXML
    private Button btn_exit;

    @FXML
    private Button btn_manage;

    @FXML
    private Button btn_order;

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void manageProducts(ActionEvent event) {

        App.loadScene("ProductManagement", 800, 600);
    }

    @FXML
    void placeOrder(ActionEvent event) {

        App.loadScene("OrderMenu", 800, 600);

    }

}
