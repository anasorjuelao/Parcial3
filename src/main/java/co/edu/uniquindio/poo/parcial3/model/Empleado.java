package co.edu.uniquindio.poo.parcial3.model;

public class Empleado extends Persona{

    private String codigo;
    private int horasDisponibles;
    private TipoEmpleado tipoEmpleado;


    public Empleado(String nombre, String cedula, String codigo, int horasDisponibles, TipoEmpleado tipoEmpleado) {
        super(nombre, cedula);
        this.codigo = codigo;
        this.horasDisponibles = horasDisponibles;
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getHorasDisponibles() {
        return horasDisponibles;
    }

    public void setHorasDisponibles(int horasDisponibles) {
        this.horasDisponibles = horasDisponibles;
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
}
