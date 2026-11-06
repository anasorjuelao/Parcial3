package co.edu.uniquindio.poo.parcial3.app;

import co.edu.uniquindio.poo.parcial3.model.Panaderia;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {

    private Stage escenario;
    public static Panaderia panaderia= new Panaderia("El Trigal");

    @Override
    public void start(Stage stage) throws Exception {
        this.escenario =escenario;
        this.escenario.setTitle("PANADERIA EL TRIGAL");
    }
}
