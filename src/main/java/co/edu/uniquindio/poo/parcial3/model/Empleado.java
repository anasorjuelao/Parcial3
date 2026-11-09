package co.edu.uniquindio.poo.parcial3.model;

public class Empleado extends Persona{

    private String codigo;
    private int minutosDisponibles;
    private TipoEmpleado tipoEmpleado;


    public Empleado(String nombre, String cedula, String codigo, int minutosDisponibles, TipoEmpleado tipoEmpleado) {
        super(nombre, cedula);
        this.codigo = codigo;
        this.minutosDisponibles = minutosDisponibles;
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getMinutosDisponibles() {
        return minutosDisponibles;
    }

    public void setMinutosDisponibles(int minutosDisponibles) {
        this.minutosDisponibles = minutosDisponibles;
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }


    public int minutosGastados(Pedido pedido){
        if(tipoEmpleado == TipoEmpleado.PANADERO_INDUSTRIAL) {
            return pedido.calcularMinutosI();
        }else if(tipoEmpleado == TipoEmpleado.PANADERO_ARTESANAL){
            return pedido.calcularMinutosA();
        }
        return 0;
    }
    public int calcularTiempoRestante(Pedido pedido){
        int tiempo=0;

        if(tipoEmpleado == TipoEmpleado.PANADERO_INDUSTRIAL){
            tiempo+= (int) (getMinutosDisponibles() - pedido.calcularMinutosI());
        }else if(tipoEmpleado == TipoEmpleado.PANADERO_ARTESANAL){
            tiempo+= (int) (getMinutosDisponibles() - pedido.calcularMinutosA());
        }
        return tiempo;
    }
}
