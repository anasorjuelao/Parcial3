package co.edu.uniquindio.poo.parcial3;

import co.edu.uniquindio.poo.parcial3.model.Panaderia;
import co.edu.uniquindio.poo.parcial3.viewController.PrimaryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

public class App extends Application {

    private Stage primaryStage;
    public static Panaderia panaderia = new Panaderia("El Trigal", new LinkedList<>(), new LinkedList<>());

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Panadería El Trigal");
        FXMLLoader loader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Parent root = loader.load();
        PrimaryController controller = loader.getController();
        controller.setApp(this);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void openGestionPedidos() {
        // Pendiente: vista de pedidos
    }

    public static void main(String[] args) {
        launch();
    }
}