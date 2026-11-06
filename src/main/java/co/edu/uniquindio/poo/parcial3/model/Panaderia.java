package co.edu.uniquindio.poo.parcial3.model;

import java.util.LinkedList;
import java.util.List;

public class Panaderia {

    private String nombre;

    private List<Empleado> listaEmpleados = new LinkedList<>();
    private List <Pedido> listaPedidos = new LinkedList<>();


    public Panaderia(String nombre, List<Empleado> listaEmpleados) {
        this.nombre = nombre;
        this.listaEmpleados = listaEmpleados;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }
    public String agregarPedido(Pedido pedido){
        boolean pedidoARegistrar = buscarPedido(pedido.getCodigoPedido());
        if(pedidoARegistrar){
            throw new IllegalArgumentException ("Ya hay un pedido con este codigo registrado.");
        }
        if(pedido.getCantidadUnidades()==0) {
            throw new IllegalArgumentException("No se registro una cantidad de unidades del pedido.");
        }
        listaPedidos.add(pedido);
        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);

        return "Pedido agregado de manera exitosa.";
    }

    private boolean buscarPedido(String codigoPedido) {
        for(Pedido p: listaPedidos){
            if(p.getCodigoPedido().equals(codigoPedido)){
                return true;
            }
        }
        return false;
    }
}
