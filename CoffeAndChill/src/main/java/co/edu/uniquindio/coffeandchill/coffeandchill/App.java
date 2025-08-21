package co.edu.uniquindio.coffeandchill.coffeandchill;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.coffeandchill.coffeandchill.model.CoffeeShop;
import co.edu.uniquindio.coffeandchill.coffeandchill.model.Menu;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        initializeCoffeeShop();
        scene = new Scene(loadFXML("OrderMenu"), 800, 600);
        stage.setScene(scene);
        stage.show();
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Carga y establece una nueva escena en la ventana principal.
     * 
     * @param fxml   El nombre del archivo FXML.
     * @param width  El ancho de la nueva escena.
     * @param height La altura de la nueva escena.
     */
    public static void loadScene(String fxml, double width, double height) {
        try {
            Parent root = loadFXML(fxml);
            scene.setRoot(root);
            scene.getWindow().setWidth(width);
            scene.getWindow().setHeight(height);

          

        } catch (IOException e) {
            showAlert("Error al cambiar la vista", "No se pudo cargar el archivo FXML: " + e.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    /**
     * Muestra una alerta con el mensaje especificado.
     * 
     * @param title   El título de la alerta.
     * @param message El contenido del mensaje.
     * @param type    El tipo de alerta.
     */
    public static void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type); // Crear la alerta
        alert.setTitle(title); // Establecer el título de la alerta
        alert.setHeaderText(null); // No usar encabezado
        alert.setContentText(message); // Establecer el contenido del mensaje
        alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
    }

    /**
     * Muestra una alerta y redirige a una nueva escena al cerrar la alerta.
     * 
     * @param title   El título de la alerta.
     * @param message El contenido del mensaje.
     * @param type    El tipo de alerta.
     * @param fxml    El nombre del archivo FXML de la nueva escena.
     * @param width   El ancho de la nueva escena.
     * @param height  La altura de la nueva escena.
     */
    public static void showAlertAndRedirect(String title, String message, AlertType type, String fxml, double width,
            double height) {
        Alert alert = new Alert(type); // Crear la alerta
        alert.setTitle(title); // Establecer el título
        alert.setHeaderText("HotCoffe"); // Sin encabezado
        alert.setContentText(message); // Mensaje

        // Redirigir a una nueva escena al cerrar la alerta
        alert.setOnHidden(evt -> loadScene(fxml, width, height));
        alert.show(); // Mostrar la alerta
    }

    private void initializeCoffeeShop() {
        CoffeeShop shop = CoffeeShop.getInstance();
        if (shop.getMenu() == null) {
            Menu menu = new Menu();
            menu.setDrinks(new ArrayList<>());
            menu.setFoods(new ArrayList<>());
            menu.setCombos(new ArrayList<>());
            shop.setMenu(menu);
        }
        if (shop.getCustomers() == null) {
            shop.setCustomers(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}