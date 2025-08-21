package co.edu.uniquindio.coffeandchill.coffeandchill.viewController;

import co.edu.uniquindio.coffeandchill.coffeandchill.App;
import co.edu.uniquindio.coffeandchill.coffeandchill.controller.ProductManagementController;
import co.edu.uniquindio.coffeandchill.coffeandchill.model.*;
import co.edu.uniquindio.coffeandchill.coffeandchill.model.enumeration.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProductManagementViewController {

    private final ProductManagementController controller;

    public ProductManagementViewController() {
        this.controller = new ProductManagementController();
    }

    @FXML
    private Button btn_add_combo;

    @FXML
    private Button btn_add_drink;

    @FXML
    private Button btn_add_food;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_back;

    @FXML
    private ComboBox<Drink> cmb_combo_drink;

    @FXML
    private ComboBox<Food> cmb_combo_food;

    @FXML
    private ComboBox<ComboType> cmb_combo_type;

    @FXML
    private ComboBox<DrinksType> cmb_drink_type;

    @FXML
    private ComboBox<FoodType> cmb_food_type;

    @FXML
    private ListView<Combo> lst_combos_management;

    @FXML
    private ListView<Drink> lst_drinks_management;

    @FXML
    private ListView<Food> lst_food_management;

    @FXML
    private TextField txt_combo_name;

    @FXML
    private TextField txt_combo_price;

    @FXML
    private TextField txt_drink_name;

    @FXML
    private TextField txt_drink_price;

    @FXML
    private TextField txt_food_name;

    @FXML
    private TextField txt_food_price;

    @FXML
    private TextField txt_drink_description;

    @FXML
    private TextField txt_food_description;

    @FXML
    private TextField txt_combo_description;

    @FXML
    public void initialize() {
        initializeComboBoxes();
        initializeListeners();
        loadInitialData();
    }

    /**
     * Inicializa los ComboBoxes con sus respectivos tipos de enumeración
     */
    private void initializeComboBoxes() {
        cmb_drink_type.getItems().addAll(DrinksType.values());
        cmb_food_type.getItems().addAll(FoodType.values());
        cmb_combo_type.getItems().addAll(ComboType.values());
    }

    /**
     * Configura los listeners para las listas de productos
     */
    private void initializeListeners() {
        setupDrinkListListener();
        setupFoodListListener();
        setupComboListListener();
    }

    /**
     * Configura el listener para la lista de bebidas
     */
    private void setupDrinkListListener() {
        lst_drinks_management.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                fillDrinkFields(newVal);
            }
        });
    }

    /**
     * Configura el listener para la lista de comidas
     */
    private void setupFoodListListener() {
        lst_food_management.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                fillFoodFields(newVal);
            }
        });
    }

    /**
     * Configura el listener para la lista de combos
     */
    private void setupComboListListener() {
        lst_combos_management.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                fillComboFields(newVal);
            }
        });
    }

    /**
     * Rellena los campos de bebida con los datos del objeto seleccionado
     */
    private void fillDrinkFields(Drink drink) {
        txt_drink_name.setText(drink.getName());
        txt_drink_price.setText(String.valueOf(drink.getPrice()));
        txt_drink_description.setText(drink.getDescription());
        cmb_drink_type.setValue(drink.getType());
    }

    /**
     * Rellena los campos de comida con los datos del objeto seleccionado
     */
    private void fillFoodFields(Food food) {
        txt_food_name.setText(food.getName());
        txt_food_price.setText(String.valueOf(food.getPrice()));
        txt_food_description.setText(food.getDescription());
        cmb_food_type.setValue(food.getType());
    }

    /**
     * Rellena los campos de combo con los datos del objeto seleccionado
     */
    private void fillComboFields(Combo combo) {
        txt_combo_name.setText(combo.getName());
        txt_combo_price.setText(String.valueOf(combo.getPrice()));
        txt_combo_description.setText(combo.getDescription());
        cmb_combo_type.setValue(combo.getType());
        cmb_combo_food.setValue(combo.getFood());
        cmb_combo_drink.setValue(combo.getDrink());
    }

    /**
     * Carga los datos iniciales en las listas y comboboxes
     */
    private void loadInitialData() {
        refreshLists();
        refreshComboBoxes();
    }

    private void refreshLists() {
        lst_drinks_management.getItems().setAll(controller.getAllDrinks());
        lst_food_management.getItems().setAll(controller.getAllFoods());
        lst_combos_management.getItems().setAll(controller.getAllCombos());
    }

    private void refreshComboBoxes() {
        cmb_combo_food.getItems().setAll(controller.getAllFoods());
        cmb_combo_drink.getItems().setAll(controller.getAllDrinks());
    }

    @FXML
    void AddDrink(ActionEvent event) {
        try {
            String name = txt_drink_name.getText().trim();
            double price = Double.parseDouble(txt_drink_price.getText().trim());
            DrinksType type = cmb_drink_type.getValue();
            String description = txt_drink_description.getText().trim();

            if (name.isEmpty() || type == null) {
                App.showAlert("Error", "All fields are required", Alert.AlertType.ERROR);
                return;
            }

            controller.addDrink(name, price, type, description);
            refreshLists();
            refreshComboBoxes();
            clearDrinkFields();
        } catch (NumberFormatException e) {
            App.showAlert("Error", "Invalid price format", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void goBack(ActionEvent event) {
        App.loadScene("StartMenu", 800, 600);
    }

    @FXML
    void addFood(ActionEvent event) {
        try {
            String name = txt_food_name.getText().trim();
            double price = Double.parseDouble(txt_food_price.getText().trim());
            FoodType type = cmb_food_type.getValue();
            String description = txt_food_description.getText().trim();

            if (name.isEmpty() || type == null) {
                App.showAlert("Error", "All fields are required", Alert.AlertType.ERROR);
                return;
            }

            controller.addFood(name, price, type, description);
            refreshLists();
            refreshComboBoxes();
            clearFoodFields();
        } catch (NumberFormatException e) {
            App.showAlert("Error", "Invalid price format", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void addCombo(ActionEvent event) {
        try {
            String name = txt_combo_name.getText().trim();
            double price = Double.parseDouble(txt_combo_price.getText().trim());
            ComboType type = cmb_combo_type.getValue();
            Food food = cmb_combo_food.getValue();
            Drink drink = cmb_combo_drink.getValue();
            String description = txt_combo_description.getText().trim();

            if (name.isEmpty() || type == null || food == null || drink == null) {
                App.showAlert("Error", "All fields are required", Alert.AlertType.ERROR);
                return;
            }

            controller.addCombo(name, price, type, food, drink, description);
            refreshLists();
            clearComboFields();
        } catch (NumberFormatException e) {
            App.showAlert("Error", "Invalid price format", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void deleteSelected(ActionEvent event) {
        Product selectedProduct = null;

        if (!lst_drinks_management.getSelectionModel().isEmpty()) {
            selectedProduct = lst_drinks_management.getSelectionModel().getSelectedItem();
        } else if (!lst_food_management.getSelectionModel().isEmpty()) {
            selectedProduct = lst_food_management.getSelectionModel().getSelectedItem();
        } else if (!lst_combos_management.getSelectionModel().isEmpty()) {
            selectedProduct = lst_combos_management.getSelectionModel().getSelectedItem();
        }

        if (selectedProduct != null) {
            controller.deleteProduct(selectedProduct);
            refreshLists();
            refreshComboBoxes();
            clearAllFields();
        }
    }

    @FXML
    void saveChanges(ActionEvent event) {
        // Este método se usaría si necesitamos persistir los cambios en algún
        // almacenamiento
        // Por ahora, como todo se mantiene en memoria, no es necesario hacer nada
        // adicional
    }

    private void clearDrinkFields() {
        txt_drink_name.clear();
        txt_drink_price.clear();
        txt_drink_description.clear();
        cmb_drink_type.setValue(null);
    }

    private void clearFoodFields() {
        txt_food_name.clear();
        txt_food_price.clear();
        txt_food_description.clear();
        cmb_food_type.setValue(null);
    }

    private void clearComboFields() {
        txt_combo_name.clear();
        txt_combo_price.clear();
        txt_combo_description.clear();
        cmb_combo_type.setValue(null);
        cmb_combo_food.setValue(null);
        cmb_combo_drink.setValue(null);
    }

    private void clearAllFields() {
        clearDrinkFields();
        clearFoodFields();
        clearComboFields();
    }

}
