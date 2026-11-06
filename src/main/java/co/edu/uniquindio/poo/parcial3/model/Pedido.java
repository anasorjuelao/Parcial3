package co.edu.uniquindio.poo.parcial3.model;

public class Pedido {
    private final String codigoPedido;
    private String nombreCliente;
    private EstadoPedido estadoPedido;
    private TipoPan tipoPan;
    private int cantidadUnidades;


    public Pedido(String codigoPedido, String nombreCliente,
                  EstadoPedido estadoPedido, TipoPan tipoPan, int cantidadUnidades) {
        this.codigoPedido = codigoPedido;
        this.nombreCliente = nombreCliente;
        this.estadoPedido = estadoPedido;
        this.tipoPan = tipoPan;
        this.cantidadUnidades= cantidadUnidades;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public TipoPan getTipoPan() {
        return tipoPan;
    }

    public void setTipoPan(TipoPan tipoPan) {
        this.tipoPan = tipoPan;
    }

    public int getCantidadUnidades() {
        return cantidadUnidades;
    }

    public void setCantidadUnidades(int cantidadUnidades) {
        this.cantidadUnidades = cantidadUnidades;
    }
}
