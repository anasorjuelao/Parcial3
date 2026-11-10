package co.edu.uniquindio.poo.parcial3.controller;

import co.edu.uniquindio.poo.parcial3.model.Empleado;
import co.edu.uniquindio.poo.parcial3.model.Panaderia;
import co.edu.uniquindio.poo.parcial3.model.Pedido;

import java.util.List;

public class PedidoController {

    Panaderia panaderia;

    public PedidoController(Panaderia panaderia) {
        this.panaderia = panaderia;
    }

    public String registrarPedido(Pedido pedido) {
        return panaderia.agregarPedido(pedido);
    }

    public void asignarPedido(String codigoPedido) {
        panaderia.asignarPedido(codigoPedido);
    }

    public void cancelarPedido(String codigoPedido) {
        panaderia.cancelarPedido(codigoPedido);
    }

    public List<Pedido> obtenerPedidos() {
        return panaderia.getListaPedidos();
    }

    public List<Empleado> obtenerEmpleados() {
        return panaderia.getListaEmpleados();
    }
}