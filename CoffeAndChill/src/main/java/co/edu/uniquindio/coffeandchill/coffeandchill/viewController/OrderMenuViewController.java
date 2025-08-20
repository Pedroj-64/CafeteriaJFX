package co.edu.uniquindio.coffeandchill.coffeandchill.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class OrderMenuViewController {

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_finish;

    @FXML
    private Button btn_new_order;

    @FXML
    private Button btn_remove;

    @FXML
    private ComboBox<?> cmb_customers;

    @FXML
    private Label lbl_total;

    @FXML
    private ListView<?> lst_combos;

    @FXML
    private ListView<?> lst_current_order;

    @FXML
    private ListView<?> lst_drinks;

    @FXML
    private ListView<?> lst_food;

    @FXML
    private TextArea txt_recommendations;

    @FXML
    void addToOrder(ActionEvent event) {

    }

    @FXML
    void finishOrder(ActionEvent event) {

    }

    @FXML
    void newOrder(ActionEvent event) {

    }

    @FXML
    void removeOrder(ActionEvent event) {

    }

}
