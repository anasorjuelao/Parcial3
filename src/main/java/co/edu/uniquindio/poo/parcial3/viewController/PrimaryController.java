package co.edu.uniquindio.poo.parcial3.viewController;

import co.edu.uniquindio.poo.parcial3.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController {

    private App app;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnPedidos;

    @FXML
    void onAbrirPedidos() {
        app.openGestionPedidos();
    }

    public void setApp(App app) {
        this.app = app;
    }
}