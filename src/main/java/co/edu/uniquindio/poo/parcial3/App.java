package co.edu.uniquindio.poo.parcial3;

import co.edu.uniquindio.poo.parcial3.model.Empleado;
import co.edu.uniquindio.poo.parcial3.model.Panaderia;
import co.edu.uniquindio.poo.parcial3.model.TipoEmpleado;
import co.edu.uniquindio.poo.parcial3.viewController.PedidoViewController;
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
        inicializarData();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Parent root = loader.load();
        PrimaryController controller = loader.getController();
        controller.setApp(this);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void openGestionPedidos() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("pedido.fxml"));
            Parent root = loader.load();
            PedidoViewController pedidoViewController = loader.getController();
            pedidoViewController.setApp(this);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Datos de prueba para ensayo
    public void inicializarData() {
        panaderia.getListaEmpleados().add(new Empleado("Ana", "1001", "E01", 100, TipoEmpleado.PANADERO_ARTESANAL));
        panaderia.getListaEmpleados().add(new Empleado("Carlos", "1002", "E02", 120, TipoEmpleado.PANADERO_INDUSTRIAL));
        panaderia.getListaEmpleados().add(new Empleado("Lucía", "1003", "E03", 120, TipoEmpleado.CAJERO));
    }

    public static void main(String[] args) {
        launch();
    }
}