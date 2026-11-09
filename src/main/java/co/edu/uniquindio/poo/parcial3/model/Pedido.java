package co.edu.uniquindio.poo.parcial3.model;

public class Pedido {
    private final String codigoPedido;
    private String nombreCliente;
    private EstadoPedido estadoPedido;
    private TipoPan tipoPan;
    private int cantidadUnidades;
    private String codigoEmpleado;
    private int minutosReservados;


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

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public int getMinutosReservados() {
        return minutosReservados;
    }

    public void setMinutosReservados(int minutosReservados) {
        this.minutosReservados = minutosReservados;
    }

    //Calcular tiempo por cada tipo de pan
    public int calcularMinutosA(){
        int horasInvertidas=0;
        int bandejas=(cantidadUnidades + 11)/12;
        if(tipoPan == TipoPan.TRADICIONAL) {
            horasInvertidas += (float) (bandejas * 30);
        }else{
            horasInvertidas+= (float) (bandejas * 40);
        }
        return horasInvertidas;
    }
    public int calcularMinutosI(){
        int horasInvertidas=0;
        int lotes=(cantidadUnidades + 29)/30;
        if(tipoPan == TipoPan.TRADICIONAL) {
            horasInvertidas += 20 + (float) (lotes * 25);
        }else{
            horasInvertidas+= 20+ (float)  (lotes * 35);
        }
        return horasInvertidas;
    }

}
