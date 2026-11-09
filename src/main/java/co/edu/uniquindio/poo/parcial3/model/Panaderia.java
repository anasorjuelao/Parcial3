package co.edu.uniquindio.poo.parcial3.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Panaderia {

    private String nombre;

    private List<Empleado> listaEmpleados = new LinkedList<>();
    private List <Pedido> listaPedidos = new LinkedList<>();


    public Panaderia(String nombre, List<Empleado> listaEmpleados, List <Pedido> listaPedidos) {
        this.nombre = nombre;
        this.listaEmpleados = listaEmpleados;
        this.listaPedidos= listaPedidos;
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

    public List<Pedido> getListaPedidos() {return listaPedidos;}

    public void setListaPedidos(List<Pedido> listaPedidos) {this.listaPedidos = listaPedidos;}

    //Metodos realizados

    public String agregarPedido(Pedido pedido){
        Optional <Pedido> pedidoARegistrar = buscarPedido(pedido.getCodigoPedido());
        if(pedidoARegistrar.isPresent()){
            throw new IllegalArgumentException ("Ya hay un pedido con este codigo registrado.");
        }
        if(pedido.getCantidadUnidades()<=0) {
            throw new IllegalArgumentException("No se registro una cantidad de unidades del pedido.");
        }
        listaPedidos.add(pedido);
        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);

        return "Pedido agregado de manera exitosa.";
    }

    private Optional <Pedido> buscarPedido(String codigoPedido) {
        for(Pedido p: listaPedidos){
            if(p.getCodigoPedido().equals(codigoPedido)){
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    //Segundo metodo solicitado
    public void asignarPedido(Empleado empleado, String codigoPedido,Pedido pedido){

        if(pedido.getEstadoPedido() == EstadoPedido.CANCELADO ||
                pedido.getEstadoPedido() == EstadoPedido.ASIGNADO){
            throw new IllegalArgumentException("No se puede asignar este pedido.");
        }

        Empleado empleadoDisponible= null;
        int menorTiempo=100000;
        // el tiempo de los panaderos
        for(Empleado e:listaEmpleados){
            int minutos= e.minutosGastados(pedido);
            if(minutos<= e.getMinutosDisponibles() && minutos < menorTiempo){
                empleadoDisponible= e;
                menorTiempo = minutos;
            }
        }
        if(empleadoDisponible == null){
            throw new IllegalStateException("No hay panaderos disponibles para " +
                    "atender este pedido.");
        }

        //Descontar el tiempo
        empleadoDisponible.setMinutosDisponibles(empleadoDisponible.
                getMinutosDisponibles() - menorTiempo);
        //Cambiar estado pedido
        pedido.setEstadoPedido(EstadoPedido.ASIGNADO);
    }

    //Metodo para cancelar un pedido
    public void cancelarPedido(Pedido pedido, Empleado empleado){

        if(pedido.getEstadoPedido() == EstadoPedido.PENDIENTE){
            pedido.setEstadoPedido(EstadoPedido.CANCELADO);
        } else if(pedido.getEstadoPedido() == EstadoPedido.ASIGNADO){
            pedido.setEstadoPedido(EstadoPedido.CANCELADO);
            empleado.setMinutosDisponibles(empleado.getMinutosDisponibles() +
                    empleado.minutosGastados(pedido));
        }else {
            throw new IllegalStateException("No se puede cancelar este pedido.");
        }
    }
}
