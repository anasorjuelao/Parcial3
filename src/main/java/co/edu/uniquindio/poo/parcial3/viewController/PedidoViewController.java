package co.edu.uniquindio.poo.parcial3.viewController;

import co.edu.uniquindio.poo.parcial3.App;
import co.edu.uniquindio.poo.parcial3.controller.PedidoController;
import co.edu.uniquindio.poo.parcial3.model.Empleado;
import co.edu.uniquindio.poo.parcial3.model.EstadoPedido;
import co.edu.uniquindio.poo.parcial3.model.Pedido;
import co.edu.uniquindio.poo.parcial3.model.TipoPan;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PedidoViewController {

    private App app;
    PedidoController pedidoController;
    ObservableList<Pedido> listPedidos = FXCollections.observableArrayList();
    ObservableList<Empleado> listEmpleados = FXCollections.observableArrayList();
    Pedido selectedPedido;

    @FXML private TextField txtCodigo;
    @FXML private TextField txtCliente;
    @FXML private ComboBox<TipoPan> cmbTipoPan;
    @FXML private TextField txtCantidad;

    @FXML private Button btnRegistrar;
    @FXML private Button btnAsignar;
    @FXML private Button btnCancelar;
    @FXML private Button btnLimpiar;

    @FXML private TableView<Pedido> tblPedidos;
    @FXML private TableColumn<Pedido, String> tbcCodigo;
    @FXML private TableColumn<Pedido, String> tbcCliente;
    @FXML private TableColumn<Pedido, String> tbcTipoPan;
    @FXML private TableColumn<Pedido, String> tbcCantidad;
    @FXML private TableColumn<Pedido, String> tbcEstado;
    @FXML private TableColumn<Pedido, String> tbcEmpleado;
    @FXML private TableColumn<Pedido, String> tbcMinutos;

    @FXML private TableView<Empleado> tblEmpleados;
    @FXML private TableColumn<Empleado, String> tbcCodigoEmp;
    @FXML private TableColumn<Empleado, String> tbcNombreEmp;
    @FXML private TableColumn<Empleado, String> tbcTipoEmp;
    @FXML private TableColumn<Empleado, String> tbcMinutosEmp;

    @FXML
    void onRegistrarPedido() {
        registrarPedido();
    }

    @FXML
    void onAsignarPedido() {
        asignarPedido();
    }

    @FXML
    void onCancelarPedido() {
        cancelarPedido();
    }

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void initialize() {
        pedidoController = new PedidoController(App.panaderia);
        initView();
    }

    private void initView() {
        cmbTipoPan.setItems(FXCollections.observableArrayList(TipoPan.values()));
        initDataBinding();
        tblPedidos.setItems(listPedidos);
        tblEmpleados.setItems(listEmpleados);
        refrescarTablas();
        listenerSelection();
    }

    private void initDataBinding() {
        tbcCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigoPedido()));
        tbcCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreCliente()));
        tbcTipoPan.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoPan().toString()));
        tbcCantidad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCantidadUnidades())));
        tbcEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstadoPedido().toString()));
        tbcEmpleado.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getCodigoEmpleado() == null ? "-" : cellData.getValue().getCodigoEmpleado()));
        tbcMinutos.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMinutosReservados())));

        tbcCodigoEmp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        tbcNombreEmp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcTipoEmp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoEmpleado().toString()));
        tbcMinutosEmp.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMinutosDisponibles())));
    }

    private void listenerSelection() {
        tblPedidos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedPedido = newSelection;
            mostrarInformacionPedido(selectedPedido);
        });
    }

    private void mostrarInformacionPedido(Pedido pedido) {
        if (pedido != null) {
            txtCodigo.setText(pedido.getCodigoPedido());
            txtCliente.setText(pedido.getNombreCliente());
            cmbTipoPan.setValue(pedido.getTipoPan());
            txtCantidad.setText(String.valueOf(pedido.getCantidadUnidades()));
        }
    }

    private void registrarPedido() {
        if (txtCodigo.getText().isEmpty() || txtCliente.getText().isEmpty() || cmbTipoPan.getValue() == null) {
            mostrarMensaje(Alert.AlertType.WARNING, "Datos incompletos", "Llene codigo, cliente y tipo de pan.");
            return;
        }
        try {
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            Pedido pedido = new Pedido(txtCodigo.getText().trim(), txtCliente.getText().trim(),
                    EstadoPedido.PENDIENTE, cmbTipoPan.getValue(), cantidad);
            String mensaje = pedidoController.registrarPedido(pedido);
            refrescarTablas();
            limpiarSeleccion();
            mostrarMensaje(Alert.AlertType.INFORMATION, "Registro", mensaje);
        } catch (NumberFormatException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "Cantidad invalida", "La cantidad debe ser un numero entero.");
        } catch (IllegalArgumentException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "No se pudo registrar", e.getMessage());
        }
    }

    private void asignarPedido() {
        String codigo = txtCodigo.getText().trim();
        if (codigo.isEmpty()) {
            mostrarMensaje(Alert.AlertType.WARNING, "Sin pedido", "Seleccione un pedido de la tabla o escriba su codigo.");
            return;
        }
        try {
            pedidoController.asignarPedido(codigo);
            refrescarTablas();
            limpiarSeleccion();
            mostrarMensaje(Alert.AlertType.INFORMATION, "Asignacion", "Pedido " + codigo + " asignado.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "No se pudo asignar", e.getMessage());
        }
    }

    private void cancelarPedido() {
        String codigo = txtCodigo.getText().trim();
        if (codigo.isEmpty()) {
            mostrarMensaje(Alert.AlertType.WARNING, "Sin pedido", "Seleccione un pedido de la tabla o escriba su codigo.");
            return;
        }
        try {
            pedidoController.cancelarPedido(codigo);
            refrescarTablas();
            limpiarSeleccion();
            mostrarMensaje(Alert.AlertType.INFORMATION, "Cancelacion", "Pedido " + codigo + " cancelado.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            mostrarMensaje(Alert.AlertType.ERROR, "No se pudo cancelar", e.getMessage());
        }
    }

    private void refrescarTablas() {
        listPedidos.setAll(pedidoController.obtenerPedidos());
        listEmpleados.setAll(pedidoController.obtenerEmpleados());
        tblPedidos.refresh();
        tblEmpleados.refresh();
    }

    private void limpiarSeleccion() {
        tblPedidos.getSelectionModel().clearSelection();
        txtCodigo.clear();
        txtCliente.clear();
        cmbTipoPan.setValue(null);
        txtCantidad.clear();
    }

    private void mostrarMensaje(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setApp(App app) {
        this.app = app;
    }
}
